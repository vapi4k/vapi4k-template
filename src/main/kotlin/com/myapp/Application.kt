package com.myapp

import com.myapp.TalkPage.talkPage
import com.vapi4k.api.buttons.ButtonColor
import com.vapi4k.api.buttons.enums.ButtonPosition
import com.vapi4k.api.buttons.enums.ButtonType
import com.vapi4k.api.model.enums.AnthropicModelType
import com.vapi4k.api.model.enums.GroqModelType
import com.vapi4k.api.model.enums.OpenAIModelType
import com.vapi4k.api.tools.Param
import com.vapi4k.api.tools.ToolCall
import com.vapi4k.api.vapi4k.enums.ServerRequestType.ASSISTANT_REQUEST
import com.vapi4k.api.vapi4k.enums.ServerRequestType.FUNCTION_CALL
import com.vapi4k.api.vapi4k.enums.ServerRequestType.TOOL_CALL
import com.vapi4k.api.voice.enums.DeepGramVoiceIdType
import com.vapi4k.api.voice.enums.ElevenLabsVoiceIdType
import com.vapi4k.api.voice.enums.ElevenLabsVoiceModelType
import com.vapi4k.api.voice.enums.PlayHTVoiceIdType
import com.vapi4k.plugin.Vapi4k
import com.vapi4k.plugin.Vapi4kServer.logger
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun main() {
  embeddedServer(
    factory = CIO,
    port = System.getenv("PORT")?.toInt() ?: 8080,  // used by Heroku
    host = "0.0.0.0",
    module = Application::module,
  ).start(wait = true)
}

fun Application.module() {
  install(Vapi4k) {
    onRequest(ASSISTANT_REQUEST, FUNCTION_CALL, TOOL_CALL) { requestContext ->
      logger.info { requestContext }
    }

    inboundCallApplication {
      onAssistantRequest { requestContext ->
        assistant {
          firstMessage = "Hello! How can I help you today?"

          openAIModel {
            modelType = OpenAIModelType.GPT_4_TURBO
            systemMessage = "You're a polite AI assistant named Vapi who is fun to talk with."

            tools {
              serviceTool(WeatherLookup())
            }
          }

          deepgramVoice {
            voiceIdType = DeepGramVoiceIdType.LUNA
          }
        }
      }
    }

    outboundCallApplication {
      serverPath = "/callCustomer"

      onAssistantRequest { args ->
        assistant {
          firstMessage = "Hello! I am calling to ask you a question."

          anthropicModel {
            modelType = AnthropicModelType.CLAUDE_3_HAIKU
            systemMessage = "You're a polite AI assistant named Vapi who is fun to talk with."

            tools {
              serviceTool(WeatherLookup())
            }
          }

          elevenLabsVoice {
            voiceIdType = ElevenLabsVoiceIdType.PAULA
            modelType = ElevenLabsVoiceModelType.ELEVEN_TURBO_V2
          }
        }
      }
    }

    webApplication {
      serverPath = "/talkApp"

      onAssistantRequest { requestContext ->
        assistant {
          firstMessage = "Hello! How can I help you today?"

          groqModel {
            modelType = GroqModelType.LLAMA3_70B
            systemMessage = "You're a polite AI assistant named Vapi who is fun to talk with."

            tools {
              serviceTool(WeatherLookup())
            }
          }

          playHTVoice {
            voiceIdType = PlayHTVoiceIdType.JACK
          }
        }
      }

      buttonConfig { requestContext ->
        position = ButtonPosition.TOP
        offset = "40px"
        width = "50px"
        height = "50px"

        idle {
          color = ButtonColor(93, 254, 202)
          type = ButtonType.PILL
          title = "Have a quick question?"
          subtitle = "Talk with our AI assistant"
          icon = "https://unpkg.com/lucide-static@0.321.0/icons/phone.svg"
        }

        loading {
          color = ButtonColor(93, 124, 202)
          type = ButtonType.PILL
          title = "Connecting..."
          subtitle = "Please wait"
          icon = "https://unpkg.com/lucide-static@0.321.0/icons/loader-2.svg"
        }

        active {
          color = ButtonColor(255, 0, 0)
          type = ButtonType.PILL
          title = "Call is in progress..."
          subtitle = "End the call."
          icon = "https://unpkg.com/lucide-static@0.321.0/icons/phone-off.svg"
        }
      }
    }
  }

  routing {
    get("/talk") {
      call.respondHtml { talkPage() }
    }
  }
}

class WeatherLookup {
  @ToolCall("Look up the weather for a city and state") // Optional
  fun weatherForCityAndState(
    @Param("The city to look up") // Optional
    city: String,
    @Param("The state to look up") // Optional
    state: String,
  ): String {
    if (city.isEmpty() || state.isEmpty())
      error("City and state must be provided")

    return "The weather in $city, $state is ${listOf("sunny", "cloudy", "rainy").random()}"
  }
}
