package com.boco.eoms.commons.statistic.customstat.model;
import org.displaytag.decorator.TableDecorator;
import com.boco.eoms.commons.statistic.base.util.StatUtil;
public class StSubscriptionHelper  extends TableDecorator {
	
	public String getStatMode() {
		StSubscription model = (StSubscription) getCurrentRowObject();
		int statMode=model.getStatMode();
		String sMode="";
		 switch (statMode) {
	      case 1: { //周报
	    	  sMode="周报";
		      break;
		      }
	      case 2: { //月报
	    	  sMode="月报";
		      break;
		      }
	      case 3: { //自定义
	    	  sMode="自定义";
		      break;
		      }
	      case 4: { //日报
	    	  sMode="日报";
		      break;
		      }
	      case 5: { //年报
	    	  sMode="年报";
		      break;
		      }
	      case 6: { //季报
	    	  sMode="季报";
		      break;
		      }
	     }
		return sMode;
	}

	public String getItem() {
		StSubscription model = (StSubscription) getCurrentRowObject();
		String item=model.getItem();
		String itemname="";
		if("commonfault-onetimepass-KPI2_oracle".equals(item)){
			itemname="故障工单_一次处理完成率统计";
		}
        if("commonfault_delay_KPI3_oracle".equals(item)){
        	itemname="故障工单_延期解决率统计";
		}if("commonfault_intime_KPI1_oracle".equals(item)){
			itemname="故障工单_故障处理及时率统计";
		}
		return itemname;
	}
//   	   public String  getSubscriber() {
//		 StSubscription model = (StSubscription) getCurrentRowObject();
//		return StatUtil.id2Name(model.getSubscriber(), "statBaseUserId2name_v35");
//		
//	}

}
