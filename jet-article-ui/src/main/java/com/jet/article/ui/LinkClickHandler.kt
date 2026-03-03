package com.jet.article.ui

import android.util.Log
import androidx.annotation.Keep
import com.jet.article.core.Link
import java.net.URISyntaxException

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

    /**
     * @param link The link to handle.
     * @param articleUrl The URL of the article, used to resolve relative links.
     */
    internal fun handleLink(
        link: String,
        articleUrl: String?,
    ) {
        val linkData = getLink(
            rawLink = link,
            articleUrl = articleUrl,
        )
        onLink(link = linkData)
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

    private fun getLink(
        rawLink: String,
        articleUrl: String?,
    ): Link {
        if (rawLink.startsWith(prefix = "#")) {
            return Link.SectionLink(rawLink = rawLink, fullLink = rawLink)
        }

        if (rawLink.startsWith(prefix = "mailto:") || rawLink.startsWith(prefix = "tel:")) {
            return Link.UriLink(rawLink = rawLink, fullLink = rawLink)
        }

        val mDomain = try {
            articleUrl?.toDomainName()
        } catch (e: URISyntaxException) {
            null
        }
        val linkDomain = try {
            rawLink.toDomainName()
        } catch (e: URISyntaxException) {
            null
        }

        val fullLink = validateLink(
            rawLink = rawLink,
            articleUrl = articleUrl
        )

        if (
            (mDomain != null && linkDomain != null)
            && mDomain == linkDomain
        ) {
            //Must be link within same domain
            return Link.SameDomainLink(rawLink = rawLink, fullLink = fullLink)
        }

        return Link.OtherDomainLink(rawLink = rawLink, fullLink = fullLink)
    }

    private fun validateLink(rawLink: String, articleUrl: String?): String {
        var fullLink = rawLink
        if (!rawLink.startsWith(prefix = "http://") && !rawLink.startsWith(prefix = "https://")) {
            val base = articleUrl?.toDomainName()?.removeSuffix(suffix = "/")
            val end = rawLink.removePrefix(prefix = "/")
            fullLink = "www.$base/$end"
        }

        return fullLink
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

private fun String.toDomainName(): String? {
    return try {
        val uri = java.net.URI(this)
        uri.host
    } catch (e: URISyntaxException) {
        null
    }
}
