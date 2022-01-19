package Type;

public class Dongle implements Type{
	   private int energy = 100;
	   private int level = 1;
	   private int cleaning = 100;
	   private int experience = 0;
	   
	   @Override
	   public void meal() {
	      System.out.println("�����̴� ���� �Ծ���!(������ +30)");
	      energy += 30;
	      cleaning -= 10;
	   }

	   @Override
	   public void sleep() {
	      System.out.println("����... �����̴� ǫ �ڰ� �Ͼ��!(������ +30)");
	      energy += 30;
	      cleaning -= 10;   
	   }

	   @Override
	   public void exercise() {
	      System.out.println("�����̴� ������ ��ߴ�!(������ -10 & ����ġ +30)");
	      energy -= 30;
	      experience += 30;
	      cleaning -= 10;   
	   }

	   @Override
	   public void play() {
	      System.out.println("�����̴� ����ְ� ��Ҵ�!(������ -10 & ����ġ +30)");
	      energy -= 30;
	      experience += 30;
	      cleaning -= 10;   
	   }

	   @Override
	   public void cleaning() {
	      System.out.println("�ֺ��� ���������. (������ -10 & û�ᵵ +50)");
	      energy -= 10;
	      cleaning += 10;   
	   }

	   @Override
	   public boolean gameover() {
	      boolean returnValue=true;
	      if(level>5) {
	         System.out.println("�����̴� 5������ �Ǿ���!");
	         returnValue=false;
	      } else if(energy<0) {
	         System.out.println("�����̴� ���� ���ߴ�...");
	         returnValue=false;
	      }
	      return returnValue;
	   }
	   
	   @Override
	   public void damainfo() {
	      System.out.println("===== ������ ���� =====");
	      System.out.println("������ : "+energy);
	      System.out.println("����ġ : "+experience);
	      System.out.println("���� : "+level);
	      System.out.println("====================");
	   }

	   @Override
	   public void levelup() {
	      if(experience>100) {
	         experience-=100;
	         level++;
	         System.out.println("�������� ������ �ö���!"+(level-1)+"->"+level);
	      }
	      
	   }

	}

