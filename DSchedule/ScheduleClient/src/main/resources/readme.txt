�ڷֲ�ʽϵͳ�У���ν����̨���ϲ����ͬһ����ʱ������ͬһ��ʱ�����ִֻ��һ�Ρ�
�����Ҫ�������Ķ�ʱ������չ�������
��ǰ��֪��ʵ�ַ����ж��֣�
 1.����ʱ������м��й�����ָ���Ļ�����ִ�У������ɶ�ʱ����ִ�е�jar�ϴ���ָ����������ִ��
 2.��ʱ�����Ƿ��ɶ�̨����ִ�У�ֻ��ͨ���������Ŀ��ƶ�ʱ����ִ�С�

��ϵͳ���õڶ��ַ�ʽ����ϵͳ�����Ҫ�����£�
  1.����zookeeper���ж�ʱ�����ע���������
  2.ͨ��rpc�����Ƹ���Ҫִ�е�����
  3.�������ĸ���zookeeperע�������ͳһ�ڵ����������ɶ�ʱ���񣬵������ĸ��ݶ�ʱ����ʱ����ʽ��
 �Ӹ������ִ�еĻ����б���ѡ��һ̨��������ִ��

ʹ�÷�ʽ��
1. ���� spring namespace:
     xmlns:config="http://www.springframework.org/schema/schedule"

 2. ���schema location:
     http://www.springframework.org/schema/schedule
     http://www.springframework.org/schema/schedule/spring-schedule-0.0.1.xsd

 3. spring xml����
<!-- ����@Scheduleע��,��ע����zookeeper��-->
<schedule:annotation-driven zkUrl="ip:port" appName="xxxx" />

2.��ʹ�����н���ע������
@Component("DemoSchedule")
public class DemoSchedule{


   @Schedule(name="",cron="")
   public void schedule(){

   }
}