/* Licensed under the Apache License, Version 2.0 (the "License");
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
 */
package org.activiti.engine.impl.persistence.entity.data.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.SuspendedJobQueryImpl;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.CachedEntityMatcher;
import org.activiti.engine.impl.persistence.entity.SuspendedJobEntity;
import org.activiti.engine.impl.persistence.entity.SuspendedJobEntityImpl;
import org.activiti.engine.impl.persistence.entity.data.AbstractDataManager;
import org.activiti.engine.impl.persistence.entity.data.SuspendedJobDataManager;
import org.activiti.engine.runtime.Job;

/**
 * @author Tijs Rademakers
 */
public class MybatisSuspendedJobDataManager extends AbstractDataManager<SuspendedJobEntity> implements SuspendedJobDataManager {

  public MybatisSuspendedJobDataManager(ProcessEngineConfigurationImpl processEngineConfiguration) {
    super(processEngineConfiguration);
  }

  @Override
  public Class<? extends SuspendedJobEntity> getManagedEntityClass() {
    return SuspendedJobEntityImpl.class;
  }

  @Override
  public SuspendedJobEntity create() {
    return new SuspendedJobEntityImpl();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Job> findJobsByQueryCriteria(SuspendedJobQueryImpl jobQuery, Page page) {
    String query = "selectSuspendedJobByQueryCriteria";
    return getDbSqlSession().selectList(query, jobQuery, page);
  }

  @Override
  public long findJobCountByQueryCriteria(SuspendedJobQueryImpl jobQuery) {
    return (Long) getDbSqlSession().selectOne("selectSuspendedJobCountByQueryCriteria", jobQuery);
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public List<SuspendedJobEntity> findJobsByTypeAndProcessDefinitionId(String jobHandlerType, String processDefinitionId) {
    Map<String, String> params = new HashMap<String, String>(2);
    params.put("handlerType", jobHandlerType);
    params.put("processDefinitionId", processDefinitionId);
    return getDbSqlSession().selectList("selectSuspendedJobByTypeAndProcessDefinitionId", params);

  }

  @Override
  public List<SuspendedJobEntity> findJobsByExecutionId(final String executionId) {
    return getList("selectSuspendedJobsByExecutionId", executionId, new CachedEntityMatcher<SuspendedJobEntity>() {

      @Override
      public boolean isRetained(SuspendedJobEntity jobEntity) {
        return jobEntity.getExecutionId() != null && jobEntity.getExecutionId().equals(executionId);
      }
    }, true);
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public List<SuspendedJobEntity> findJobsByProcessInstanceId(final String processInstanceId) {
    return getDbSqlSession().selectList("selectSuspendedJobsByProcessInstanceId", processInstanceId);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<SuspendedJobEntity> findJobsByTypeAndProcessDefinitionKeyNoTenantId(String jobHandlerType, String processDefinitionKey) {
    Map<String, String> params = new HashMap<String, String>(2);
    params.put("handlerType", jobHandlerType);
    params.put("processDefinitionKey", processDefinitionKey);
    return getDbSqlSession().selectList("selectSuspendedJobByTypeAndProcessDefinitionKeyNoTenantId", params);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<SuspendedJobEntity> findJobsByTypeAndProcessDefinitionKeyAndTenantId(String jobHandlerType, String processDefinitionKey, String tenantId) {
    Map<String, String> params = new HashMap<String, String>(3);
    params.put("handlerType", jobHandlerType);
    params.put("processDefinitionKey", processDefinitionKey);
    params.put("tenantId", tenantId);
    return getDbSqlSession().selectList("selectSuspendedJobByTypeAndProcessDefinitionKeyAndTenantId", params);
  }

  @Override
  public void updateJobTenantIdForDeployment(String deploymentId, String newTenantId) {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("deploymentId", deploymentId);
    params.put("tenantId", newTenantId);
    getDbSqlSession().update("updateSuspendedJobTenantIdForDeployment", params);
  }

}
