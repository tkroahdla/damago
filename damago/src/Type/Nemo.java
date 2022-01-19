package Type;

public class Nemo implements Type {
	   private int energy = 100;
	   private int level = 1;
	   private int cleaning = 100;
	   private int experience = 0;

	   @Override
	   public void meal() {
	      System.out.println("네모는 밥을 먹었다!(에너지 +30)");
	      energy += 30;
	      cleaning -= 10;
	   }

	   @Override
	   public void sleep() {
	      System.out.println("쿨쿨... 네모는 푹 자고 일어났다!(에너지 +30)");
	      energy += 30;
	      cleaning -= 10;
	   }

	   @Override
	   public void exercise() {
	      System.out.println("네모는 열심히 운동했다!(에너지 -10 & 경험치 +30)");
	      energy -= 30;
	      experience += 30;
	      cleaning -= 10;
	   }

	   @Override
	   public void play() {
	      System.out.println("네모는 재미있게 놀았다!(에너지 -10 & 경험치 +30)");
	      energy -= 30;
	      experience += 30;
	      cleaning -= 10;
	   }

	   @Override
	   public void cleaning() {
	      System.out.println("주변이 깔끔해졌다. (에너지 -10 & 청결도 +50)");
	      energy -= 10;
	      cleaning += 10;
	   }

	   @Override
	   public boolean gameover() {
	      boolean returnValue = true;
	      if (level > 5) {
	         System.out.println("네모는 5레벨이 되었다!");
	         returnValue = false;
	      } else if (energy < 0) {
	         System.out.println("네모는 힘이 다했다...");
	         returnValue = false;
	      }
	      return returnValue;
	   }

	   @Override
	   public void damainfo() {
	      System.out.println("====== 네모 정보 ======");
	      System.out.println("에너지 : " + energy);
	      System.out.println("경험치 : " + experience);
	      System.out.println("레벨 : " + level);
	      System.out.println("=====================");
	   }

	   @Override
	   public void levelup() {
	      if (experience > 100) {
	         experience -= 100;
	         level++;
	         System.out.println("네모의 레벨이 올랐다!" + (level - 1) + "->" + level);
	      }
	   }
	}
