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
package org.apache.cxf.rs.security.oauth2.jwe;

import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.cxf.rs.security.oauth2.jwt.Algorithm;

public class RSAOaepKeyEncryption extends AbstractWrapKeyAlgorithm {
    private static final Set<String> SUPPORTED_ALGORITHMS = new HashSet<String>(
        Arrays.asList(Algorithm.RSA_OAEP.getJwtName(),
                      Algorithm.RSA_OAEP_256.getJwtName()));
    public RSAOaepKeyEncryption(RSAPublicKey publicKey) {
        this(publicKey, null, true);
    }
    public RSAOaepKeyEncryption(RSAPublicKey publicKey, boolean wrap) {
        this(publicKey, null, wrap);
    }
    public RSAOaepKeyEncryption(RSAPublicKey publicKey, String jweAlgo) {
        this(publicKey, jweAlgo, true);
    }
    public RSAOaepKeyEncryption(RSAPublicKey publicKey, String jweAlgo, boolean wrap) {
        super(publicKey, jweAlgo, wrap, SUPPORTED_ALGORITHMS);
    }
    
}
