/*
 *
 *  *  Copyright 2010-2016 OrientDB LTD (http://orientdb.com)
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *       http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *  *
 *  * For more information: http://orientdb.com
 *
 */
package com.orientechnologies.orient.server.network.protocol.http.command.get;

import com.orientechnologies.orient.core.OConstants;
import com.orientechnologies.orient.server.OServerInfo;
import com.orientechnologies.orient.server.network.protocol.http.OHttpRequest;
import com.orientechnologies.orient.server.network.protocol.http.OHttpResponse;
import com.orientechnologies.orient.server.network.protocol.http.OHttpUtils;

public class OServerCommandGetServerVersion extends OServerCommandGetConnections {
  private static final String[] NAMES = { "GET|server/version" };

  public OServerCommandGetServerVersion() {
    super("server.info");
  }

  @Override
  public String[] getNames() {
    return NAMES;
  }

  @Override
  public boolean authenticate(final OHttpRequest iRequest, final OHttpResponse iResponse, final boolean iAskForAuthentication) {
    // return always true, as authentication is not needed
    return true;
  }

  @Override
  public boolean execute(final OHttpRequest iRequest, OHttpResponse iResponse) throws Exception {
    checkSyntax(iRequest.url, 1, "Syntax error: server");

    iRequest.data.commandInfo = "Server status";

    final String result = OConstants.ORIENT_VERSION;

    iResponse.send(OHttpUtils.STATUS_OK_CODE, OHttpUtils.STATUS_OK_DESCRIPTION, OHttpUtils.CONTENT_JSON, result, null);

    return false;
  }
}
