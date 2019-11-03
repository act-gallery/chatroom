package demo.chatroom;

/*-
 * #%L
 * chatroom
 * %%
 * Copyright (C) 2018 ActFramework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import act.cli.CliContext;
import act.cli.Command;
import act.cli.Required;
import act.util.Lazy;
import act.ws.WebSocketConnectionManager;
import okhttp3.*;
import org.osgl.util.S;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Lazy
public class ConsoleApp {

    @Inject
    private WebSocketConnectionManager connectionManager;

    @Command(name = "send", help = "Send message to websocket server")
    public void send(@Required("specify the message to be sent") String message) {
        connectionManager.sendToUrl(message + " - admin broadcast", "/msg");
    }


}
