package com.boco.eoms.workbench.infopub.test.aop;

public class ApuKwikEMart  implements KwikEMart {
	private boolean cheeseIsEmpty = false;
	 
    private boolean pepperIsEmpty = false;

    private boolean squishIsEmpty = false;

    public Cheese buyCheese(Customer customer) {

         Cheese s = new Cheese();
         System.out.println("--我想买:" + s);
         return s;

    }

    public Pepper buyPepper(Customer customer) {


         Pepper s = new Pepper();
         System.out.println("--我想买:" + s);
         return s;

    }

    public Squish buySquish(Customer customer) {



         Squish s = new Squish();
         System.out.println("--我想买:" + s);
         return s;

    }

    public void setCheeseIsEmpty(boolean cheeseIsEmpty) {
         this.cheeseIsEmpty = cheeseIsEmpty;
    }

    public void setPepperIsEmpty(boolean pepperIsEmpty) {
         this.pepperIsEmpty = pepperIsEmpty;
    }

    public void setSquishIsEmpty(boolean squishIsEmpty) {
         this.squishIsEmpty = squishIsEmpty;
    }

}
