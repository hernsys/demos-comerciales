package com.projects.examples.jbpm.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.projects.examples.jbpm.workItemsHandlers.BloodPressureWorkItemHandler;

public class JbpmTestCases {

	private static final String PROCESS_ID = "HR.demoComercial";
	private static final String BLOOD_PRESSURE_WIH = "BloodPressure";
	private static final String PROCESS_BPMN = "com/projects/examples/bpmn/testProcess.bpmn";

	@Test
	public void testProcessBadNews() {
		StatefulKnowledgeSession ksession = JbpmUtils
				.createKnowledgeSession(PROCESS_BPMN);
		Map<String, Object> params = new HashMap<String, Object>();

		BloodPressureWorkItemHandler bloodPressure = new BloodPressureWorkItemHandler();

		params.put("presion", 20);
		ksession.getWorkItemManager().registerWorkItemHandler(
				BLOOD_PRESSURE_WIH, bloodPressure);

		ProcessInstance processInstance = ksession.startProcess(PROCESS_ID,
				params);
		Assert.assertNotNull(processInstance);

		JbpmUtils.sessionDispose(ksession);

	}

	@Test
	public void testProcessGoodNews() {
		StatefulKnowledgeSession ksession = JbpmUtils
				.createKnowledgeSession(PROCESS_BPMN);
		Map<String, Object> params = new HashMap<String, Object>();

		BloodPressureWorkItemHandler bloodPressure = new BloodPressureWorkItemHandler();

		params.put("presion", 12);
		ksession.getWorkItemManager().registerWorkItemHandler(
				BLOOD_PRESSURE_WIH, bloodPressure);

		ProcessInstance processInstance = ksession.startProcess(PROCESS_ID,
				params);
		Assert.assertNotNull(processInstance);

		JbpmUtils.sessionDispose(ksession);
	}

}
