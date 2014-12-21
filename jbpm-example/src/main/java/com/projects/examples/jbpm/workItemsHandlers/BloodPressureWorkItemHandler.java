package com.projects.examples.jbpm.workItemsHandlers;

import org.drools.core.process.instance.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.kie.api.runtime.process.WorkItem;

public class BloodPressureWorkItemHandler implements WorkItemHandler {
	
	private long workItemId;
    private WorkItemManager manager;

	@Override
	public void abortWorkItem(WorkItem arg0, WorkItemManager arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		System.out.println("** WorkItemHandler1 was called");
		
		String presion = (String) workItem.getParameter("presion");
		
		System.out.println("presion: " + presion);
		
        this.workItemId =  workItem.getId();
        this.manager = manager;
        
        
        this.complete();
        
		
	}
	
	public void complete() {
        this.manager.completeWorkItem(workItemId, null);
    }
    
    public long getWorkItemId() {
        return workItemId;
    }

}
