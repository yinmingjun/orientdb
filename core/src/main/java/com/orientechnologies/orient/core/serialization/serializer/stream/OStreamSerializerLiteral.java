/*
  *
  *  *  Copyright 2014 Orient Technologies LTD (info(at)orientechnologies.com)
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
  *  * For more information: http://www.orientechnologies.com
  *
  */
package com.orientechnologies.orient.core.serialization.serializer.stream;

import com.orientechnologies.orient.core.metadata.schema.OType;
import com.orientechnologies.orient.core.serialization.serializer.record.string.ORecordSerializerStringAbstract;

import java.io.IOException;

public class OStreamSerializerLiteral implements OStreamSerializer {
	public static final String										NAME			= "li";

	public static final OStreamSerializerLiteral	INSTANCE	= new OStreamSerializerLiteral();

	public String getName() {
		return NAME;
	}

	public Object fromStream(final byte[] iStream) throws IOException {
		return ORecordSerializerStringAbstract.getTypeValue(new String(iStream,"UTF-8"));
	}

	public byte[] toStream(final Object iObject) throws IOException {
		if (iObject == null)
			return null;

		final StringBuilder buffer = new StringBuilder();
		ORecordSerializerStringAbstract.fieldTypeToString(buffer, OType.getTypeByClass(iObject.getClass()), iObject,
				false);
		return buffer.toString().getBytes("UTF-8");
	}
}
