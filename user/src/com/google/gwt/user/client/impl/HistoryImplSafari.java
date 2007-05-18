/*
 * Copyright 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.user.client.impl;

import com.google.gwt.user.client.Element;

/**
 * Safari implementation of
 * {@link com.google.gwt.user.client.impl.HistoryImplFrame}.
 */
class HistoryImplSafari extends HistoryImplFrame {

  protected native String getTokenElementContent(Element tokenElement) /*-{
    return tokenElement.value;
  }-*/;
  
  protected native void injectGlobalHandler() /*-{
    $wnd.__gwt_onHistoryLoad = function(token) {
      // Change the URL and notify the application that its history frame
      // is changing.
      if (token != $wnd.__gwt_historyToken) {
        $wnd.__gwt_historyToken = token;
        // TODO(jgw): fix the bookmark update, if possible.  The following code
        // screws up the browser by (a) making it pretend that it's loading the
        // page indefinitely, and (b) causing all text to disappear (!)
//        $wnd.location.hash = encodeURIComponent(token);
        @com.google.gwt.user.client.impl.HistoryImpl::onHistoryChanged(Ljava/lang/String;)(token);
      }
    };
  }-*/;
  
  protected native void newItemImpl(Element historyFrame, String historyToken) /*-{
    historyToken = historyToken || "";
    historyFrame.contentWindow.location.href = 'history.html?' + historyToken;
  }-*/;

}
