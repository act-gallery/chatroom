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

import act.Act;
import act.app.ActionContext;
import act.ws.WebSocketContext;
import org.osgl.mvc.annotation.Before;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.WsAction;
import org.osgl.util.S;

import static act.controller.Controller.Util.redirect;

@SuppressWarnings("unused")
public class ChatApp {

    @Before(only = "home")
    public void ensureLogin(ActionContext context) {
        if (!context.isLoggedIn()) {
            throw redirect("/login");
        }
    }

    @GetAction
    public void home() {
    }

    @GetAction("login")
    public void loginForm() {
    }

    @PostAction("login")
    public void login(String username, ActionContext context) {
        context.login(username);
        redirect("/");
    }

    @WsAction("msg")
    public void onMessage(String message, WebSocketContext context) {
        // suppress blank lines
        if (S.notBlank(message)) {
            context.sendToPeers(message + " - " + context.actionContext().username());
        }
    }

    public static void main(String[] args) throws Exception {
        Act.start();
    }
}
