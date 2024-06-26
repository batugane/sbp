/*
 * Copyright (C) 2019-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.laxture.sbp.spring.boot.configurer;

import org.laxture.sbp.spring.boot.IPluginConfigurer;
import org.laxture.sbp.spring.boot.SpringBootstrap;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author <a href="https://github.com/hank-cp">Hank CP</a>
 */
public class SbpSharedServiceConfigurer implements IPluginConfigurer {

    @Override
    public String[] excludeConfigurations() {
        return new String[] {
            // async
            "org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration",
            "org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration",
            "org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration",
            // clients
            "org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration",
            "org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration",
            "org.springframework.boot.autoconfigure.web.reactive.function.client.ClientHttpConnectorAutoConfiguration",
            "org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration",
            // misc
            "org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration",
            "org.springframework.boot.autoconfigure.graphql.GraphQlAutoConfiguration",
            "org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration"
        };
    }

    @Override
    public void onBootstrap(SpringBootstrap bootstrap,
                            GenericApplicationContext pluginApplicationContext) {
        // async
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "quartzScheduler");
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "taskExecutorBuilder");
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "applicationTaskExecutor");
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "taskScheduler");
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "taskSchedulerBuilder");
        // clients
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "restTemplateBuilderConfigurer");
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "restTemplateBuilder");
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "multipartResolver");
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "multipartConfigElement");
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "webClientHttpConnector");
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "webClientHttpConnectorCustomizer");
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "webClientSsl");
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "webClientBuilder");
        // misc.
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "jacksonObjectMapper");
        bootstrap.importBeanFromMainContext(pluginApplicationContext, "messageConverters");
    }
}
