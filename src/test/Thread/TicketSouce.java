package test.Thread;

class TicketSouce implements Runnable
{
    //Ʊ������
    private int ticket=100;
    public void run()
    {
        for(int i=1;i<50;i++)
        {
            if(ticket>0)
            {
                //����1s���У�Ϊ��ʹЧ�������ԣ�������ܳ�����Ч��
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"�Ŵ�������"+this.ticket--+"��Ʊ");
            }
        }
    }
}

 