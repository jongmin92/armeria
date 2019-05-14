/*
 * Copyright 2019 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.armeria.server.annotation;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.testng.Assert;

import com.linecorp.armeria.common.AggregatedHttpMessage;
import com.linecorp.armeria.common.HttpData;
import com.linecorp.armeria.common.MediaType;
import com.linecorp.armeria.server.ServiceRequestContext;

public class ByteArrayRequestConverterFunctionTest {

    private static final RequestConverterFunction function = new ByteArrayRequestConverterFunction();
    private static final ServiceRequestContext ctx = mock(ServiceRequestContext.class);
    private static final AggregatedHttpMessage req = mock(AggregatedHttpMessage.class);

    static final String JSON_TEXT = "{\"key\": \"value\"}";

    @Test
    public void jsonTextToByteArray() throws Exception {
        when(req.contentType()).thenReturn(MediaType.JSON);
        when(req.content()).thenReturn(HttpData.ofUtf8(JSON_TEXT));

        final Object result = function.convertRequest(ctx, req, byte[].class);
        Assert.assertTrue(result instanceof byte[]);
    }

    @Test
    public void jsonTextToHttpData() throws Exception {
        when(req.contentType()).thenReturn(MediaType.JSON);
        when(req.content()).thenReturn(HttpData.ofUtf8(JSON_TEXT));

        final Object result = function.convertRequest(ctx, req, HttpData.class);
        Assert.assertTrue(result instanceof HttpData);
    }
}
