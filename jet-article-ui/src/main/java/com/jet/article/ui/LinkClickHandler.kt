package com.jet.article.ui

import android.util.Log
import androidx.annotation.Keep
import androidx.core.net.toUri
import com.jet.article.core.Link

/**
 * @param callback
 * @see LinkCallback
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 06.02.2024
 */
@Keep
class LinkClickHandler internal constructor() {

    var callback: LinkCallback? = null

    fun handleCustomUrl(customUrl: String) {
        val uri = customUrl.toUri()
        if (uri.scheme != "jetarticle") {
            return
        }

        val link = when (uri.authority) {
            "same-domain" -> Link.SameDomainLink(
                rawLink = uri.getQueryParameter("url") ?: "",
                fullLink = uri.getQueryParameter("url") ?: ""
            )
            "other-domain" -> Link.OtherDomainLink(
                rawLink = uri.getQueryParameter("url") ?: "",
                fullLink = uri.getQueryParameter("url") ?: ""
            )
            "section" -> Link.SectionLink(
                rawLink = uri.getQueryParameter("id") ?: "",
                fullLink = uri.getQueryParameter("id") ?: ""
            )
            "uri" -> Link.UriLink(
                rawLink = uri.getQueryParameter("uri") ?: "",
                fullLink = uri.getQueryParameter("uri") ?: ""
            )
            else -> null
        }

        if (link != null) {
            onLink(link = link)
        }
    }

    private fun onLink(link: Link) {
        if (callback == null) {
            Log.d("LinkClickHandler", "onLink called but callback is null")
            return
        }

        when (link) {
            is Link.UriLink -> callback?.onUriLink(link = link)
            is Link.SectionLink -> callback?.onSectionLink(link = link)
            is Link.SameDomainLink -> callback?.onSameDomainLink(link = link)
            is Link.OtherDomainLink -> callback?.onOtherDomainLink(link = link)
        }
    }

    /**
     * @since 1.0.0
     */
    @Keep
    open class LinkCallback {

        /**
         * @since 1.0.0
         */
        open fun onSectionLink(
            link: Link.SectionLink,
        ) = Unit

        /**
         * @since 1.0.0
         */
        open fun onSameDomainLink(
            link: Link.SameDomainLink
        ) = Unit

        /**
         * @since 1.0.0
         */
        open fun onOtherDomainLink(
            link: Link.OtherDomainLink,
        ) = Unit

        /**
         * @since 1.0.0
         */
        open fun onUriLink(
            link: Link.UriLink,
        ) = Unit
    }
}