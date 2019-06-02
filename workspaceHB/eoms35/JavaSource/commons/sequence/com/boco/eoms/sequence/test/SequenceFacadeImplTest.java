package com.boco.eoms.sequence.test;

import junit.framework.TestCase;

import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.Sequence;
import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;

/**
 * <p>
 * Title:测试序列工作
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 26, 2008 11:13:10 AM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class SequenceFacadeImplTest extends TestCase {
	private ISequenceFacade sequenceFacade;

	protected void setUp() throws Exception {
		sequenceFacade = SequenceLocator.getSequenceFacade();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testDoJob() {

		Helper helper = new Helper();
		sequenceFacade.doJob(helper, "out",
				new Class[] { java.lang.String.class },
				new Object[] { "-----1-----" }, null);

		sequenceFacade.doJob(helper, "out",
				new Class[] { java.lang.String.class },
				new Object[] { "-----2-----" }, null);

		sequenceFacade.doJob(helper, "out",
				new Class[] { java.lang.String.class },
				new Object[] { "-----3-----" }, null);

	}

	public void testDoJobSequence() {
//		Sequence sequence = sequenceFacade.getSequence();
		Helper helper = new Helper();
//		sequenceFacade.put(helper, "out",
//				new Class[] { java.lang.String.class },
//				new Object[] { "-----1-----" }, null, sequence);
//		sequenceFacade.put(helper, "out",
//				new Class[] { java.lang.String.class },
//				new Object[] { "-----2-----" }, null, sequence);
//		sequenceFacade.put(helper, "out",
//				new Class[] { java.lang.String.class },
//				new Object[] { "-----3-----" }, null, sequence);
//		sequenceFacade.doJob(sequence);
//
//		Sequence dutySequence = null;
//		try {
//			dutySequence = sequenceFacade.getSequence("duty");
//		} catch (SequenceNotFoundException e) {
//			e.printStackTrace();
//		}
//		sequenceFacade.put(helper, "out",
//				new Class[] { java.lang.String.class },
//				new Object[] { "-----duty1-----" }, null, dutySequence);
//		sequenceFacade.put(helper, "out",
//				new Class[] { java.lang.String.class },
//				new Object[] { "-----duty2-----" }, null, dutySequence);
//		sequenceFacade.put(helper, "out",
//				new Class[] { java.lang.String.class },
//				new Object[] { "-----duty3-----" }, null, dutySequence);
//		
//		sequenceFacade.doJob(dutySequence);
		
		
		Sequence dutySequence = null;
		try {
			dutySequence = sequenceFacade.getSequence("duty");
		} catch (SequenceNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 10000; i++) {

			sequenceFacade.put(helper, "outtenthousand",
					new Class[] { java.lang.String.class },
					new Object[] { "1threadduty" + i }, null, dutySequence);
		}
		dutySequence.setChanged();
		sequenceFacade.doJob(dutySequence);
		 for (int i = 0; i < 10000; i++) {
			SequenceLocator.getSequenceFacade().put(helper, "outtenthousand",
					new Class[] { java.lang.String.class },
					new Object[] { "2threadduty" + i }, null, dutySequence);
		}
		 dutySequence.setChanged();
		
		sequenceFacade.doJob(dutySequence);

	}

}
