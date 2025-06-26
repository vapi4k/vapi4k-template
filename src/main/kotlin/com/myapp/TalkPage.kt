package com.myapp

import com.github.pambrose.common.json.toJsonElement
import com.vapi4k.dsl.web.VapiWeb.vapiTalkButton
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.title

object TalkPage {
  fun HTML.talkPage() {
    head {
      title { +"Talk Button Demo" }
    }
    body {
      h1 { +"Talk Button Demo" }
      vapiTalkButton {
        serverPath = "/talkApp"
        // post args are optional
        postArgs = mapOf(
          "arg1" to "10",
          "arg2" to "20",
          "name" to "Jane",
        ).toJsonElement()
      }
    }
  }
}
