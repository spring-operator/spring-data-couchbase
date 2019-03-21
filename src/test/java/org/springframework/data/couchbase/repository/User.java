/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.data.couchbase.repository;

import org.springframework.data.annotation.Id;

/**
 * @author Michael Nitschinger
 */
public class User {

  @Id
  private final String key;

  private final String username;

  public User(String key, String username) {
    this.key = key;
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public String getKey() {
    return key;
  }

}
