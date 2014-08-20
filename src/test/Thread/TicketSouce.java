package test.Thread;

class TicketSouce implements Runnable
{
    //票的总数
    private int ticket=100;
    public void run()
    {
        for(int i=1;i<50;i++)
        {
            if(ticket>0)
            {
                //休眠1s秒中，为了使效果更明显，否则可能出不了效果
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"号窗口卖出"+this.ticket--+"号票");
            }
        }
    }
}

 