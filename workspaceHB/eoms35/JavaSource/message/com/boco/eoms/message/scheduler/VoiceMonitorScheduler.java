package com.boco.eoms.message.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.EOMSAttributes;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.message.mgr.IVoiceMonitorManager;
import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.Sequence;
import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;

public class VoiceMonitorScheduler implements Job {
	private static int count = 0;
	
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		String sequenceOpen = StaticMethod
				.null2String(((EOMSAttributes) ApplicationContextHolder
						.getInstance().getBean("eomsAttributes"))
						.getSequenceOpen());
		if ("true".equals(sequenceOpen)) {
			// 初始化队列
			ISequenceFacade sequenceFacade = SequenceLocator
					.getSequenceFacade();
			Sequence msgSequence = null;
			try {
				msgSequence = sequenceFacade.getSequence("voice");
			} catch (SequenceNotFoundException e) {
				e.printStackTrace();
			}
			// 初始化mgr
			IVoiceMonitorManager mgr = (IVoiceMonitorManager) ApplicationContextHolder
					.getInstance().getBean("IvoiceMonitorManager");
			// 把mgr撇队列里
			sequenceFacade.put(mgr, "sendVoice4Schedule", null, null, null,
					msgSequence);
			msgSequence.setChanged();
			sequenceFacade.doJob(msgSequence);
		} else {
						
				IVoiceMonitorManager mgr = (IVoiceMonitorManager) ApplicationContextHolder
					.getInstance().getBean("IvoiceMonitorManager");
			mgr.sendVoice4Schedule();
		}
		
	}
}
