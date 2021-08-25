/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * The OpenSearch Contributors require contributions made to
 * this file be licensed under the Apache-2.0 license or a
 * compatible open source license.
 *
 * Modifications Copyright OpenSearch Contributors. See
 * GitHub history for details.
 */

/*
 *   Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License").
 *   You may not use this file except in compliance with the License.
 *   A copy of the License is located at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   or in the "license" file accompanying this file. This file is distributed
 *   on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *   express or implied. See the License for the specific language governing
 *   permissions and limitations under the License.
 */

package org.opensearch.alerting.action

import org.junit.Assert
import org.opensearch.alerting.randomBucketLevelMonitorRunResult
import org.opensearch.alerting.randomQueryLevelMonitorRunResult
import org.opensearch.common.io.stream.BytesStreamOutput
import org.opensearch.common.io.stream.StreamInput
import org.opensearch.test.OpenSearchTestCase

class ExecuteMonitorResponseTests : OpenSearchTestCase() {

    fun `test exec query-level monitor response`() {
        val req = ExecuteMonitorResponse(randomQueryLevelMonitorRunResult())
        Assert.assertNotNull(req)

        val out = BytesStreamOutput()
        req.writeTo(out)
        val sin = StreamInput.wrap(out.bytes().toBytesRef().bytes)
        val newReq = ExecuteMonitorResponse(sin)
        assertNotNull(newReq.monitorRunResult)
        assertEquals("test-monitor", newReq.monitorRunResult.monitorName)
        assertNotNull(newReq.monitorRunResult.inputResults)
    }

    fun `test exec bucket-level monitor response`() {
        val req = ExecuteMonitorResponse(randomBucketLevelMonitorRunResult())
        Assert.assertNotNull(req)

        val out = BytesStreamOutput()
        req.writeTo(out)
        val sin = StreamInput.wrap(out.bytes().toBytesRef().bytes)
        val newReq = ExecuteMonitorResponse(sin)
        assertNotNull(newReq.monitorRunResult)
        assertEquals("test-monitor", newReq.monitorRunResult.monitorName)
        assertNotNull(newReq.monitorRunResult.inputResults)
    }
}
