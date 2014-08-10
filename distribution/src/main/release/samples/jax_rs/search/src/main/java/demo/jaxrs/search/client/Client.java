/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package demo.jaxrs.search.client;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.cxf.helpers.IOUtils;

public final class Client {
    private Client() {
    }

    public static void main(String args[]) throws Exception {               
        final String url = "http://localhost:9000/catalog";
        final HttpClient httpClient = new HttpClient();
                        
        uploadToCatalog(url, httpClient, "jsr339-jaxrs-2.0-final-spec.pdf");
        uploadToCatalog(url, httpClient, "JavaWebSocketAPI_1.0_Final.pdf");
        list(url, httpClient);
    }

    private static void list(final String url, final HttpClient httpClient) 
        throws IOException, HttpException {
        
        System.out.println("Sent HTTP GET request to query all books in catalog");
        
        final GetMethod get = new GetMethod(url);
        try {
            int status = httpClient.executeMethod(get);
            if (status == 200) {   
                System.out.println(get.getResponseBodyAsString());
            }
        } finally {
            get.releaseConnection();
        }
    }

    private static void uploadToCatalog(final String url, final HttpClient httpClient,
            final String filename) throws IOException, HttpException {
        
        System.out.println("Sent HTTP POST request to upload the file into catalog: " + filename);
        
        final PostMethod post = new PostMethod(url);
        final Part[] parts = {
            new FilePart(filename,
                new ByteArrayPartSource(filename, 
                    IOUtils.readBytesFromStream(Client.class.getResourceAsStream("/" + filename)) 
                ) 
            )
        };
        
        post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
        
        try {
            int status = httpClient.executeMethod(post);
            if (status == 201) {   
                System.out.println(post.getResponseHeader("Location"));
            }
        } finally {
            post.releaseConnection();
        }
    }

}
