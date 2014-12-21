package com.projects.examples.jbpm.test;

import org.drools.core.io.impl.ClassPathResource;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class JbpmUtils {

	public static StatefulKnowledgeSession createKnowledgeSession(String process) {
		// Create the kbuilder
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();

		// Add simpleProcess.bpmn to kbuilder
		kbuilder.add(new ClassPathResource(process), ResourceType.BPMN2);
		System.out.println("Compiling resources");

		// Check for errors
		if (kbuilder.hasErrors()) {
			if (kbuilder.getErrors().size() > 0) {
				for (KnowledgeBuilderError error : kbuilder.getErrors()) {
					System.out.println("Error building kbase: "
							+ error.getMessage());
				}
			}
			throw new RuntimeException("Error building kbase!");
		}

		// Create a knowledge base and add the generated package
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

		// return a new stateful session
		return kbase.newStatefulKnowledgeSession();
	}

	/**
	 * Dispose the knowledge session
	 */
	public static void sessionDispose(StatefulKnowledgeSession ksession) {
		ksession.dispose();
	}

}
