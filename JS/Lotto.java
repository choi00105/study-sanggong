import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Lotto {
    public static void main(String[] args) {
        // 지난 회차의 당첨 번호와 추첨 받을 배열 선언
        int[] winningNumbers = {3, 6, 9, 18, 22, 35};
        int[] lottoNumbers = new int[6];
        int bonusNumber = 14;
        

        // 로또 번호 랜덤으로 추첨
        Random random = new Random();
        for (int i = 0; i < lottoNumbers.length; i++) {
            int num = random.nextInt(45) + 1;
            if (contains(lottoNumbers, num)) {
                i--;
            } else {
                lottoNumbers[i] = num;
            }
        }

        // 뽑은 번호 정렬
        Arrays.sort(lottoNumbers);

        // 로또 번호 출력
        System.out.print("로또 당첨 번호 출력 ->\t");
        for (int i = 0; i < 6; i++) {
            System.out.print(winningNumbers[i] + " ");
        }
        System.out.println();

        System.out.print("추첨 된 번호 출력 ->\t");
        for (int i = 0; i < 6; i++) {
            System.out.print(lottoNumbers[i] + " ");
        }
        System.out.println();

        // 당첨된 번호 갯수 계산
        int count = 0;
        int checkBonusNumber = 0;
        for (int i = 0; i < lottoNumbers.length; i++) {
            if (contains(winningNumbers, lottoNumbers[i])) {
                count++;
            }
            if (lottoNumbers[i] == bonusNumber) {
                checkBonusNumber++;
            }
        }

        // 당첨 결과 출력
        if (count == 6) {
            System.out.println("1등에 당첨되었습니다!");
        } else if (count == 5 && checkBonusNumber==1) {
            System.out.println("2등에 당첨되었습니다!");
        } else if (count == 5) {
            System.out.println("3등에 당첨되었습니다!");
        } else if (count == 4) {
            System.out.println("4등에 당첨되었습니다!");
        } else if (count == 3) {
            System.out.println("5등에 당첨되었습니다!");
        } else {
            System.out.println("꽝입니다. ㅠㅠ");
        }
    }
    public static int[] generateLottoNumbers() {
        Random random = new Random();
        int[] lottoNumbers = new int[6];
        for (int i = 0; i < lottoNumbers.length; i++) {
        int num = random.nextInt(45) + 1;
        if (contains(lottoNumbers, num)) {
        i--;
        } else {
        lottoNumbers[i] = num;
        }
        }
        return lottoNumbers;
    }

    
    // 뽑은 번호 배열에 당첨 숫자 포함 여부 확인 메소드
    public static boolean contains(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }
   
    
        public static List<Integer> generateLottoNumbers(int count) {
            List<Integer> lottoNumbers = new ArrayList<>();
            Random random = new Random();
            
            while(lottoNumbers.size() < count) {
                int num = random.nextInt(45) + 1;
                if(!lottoNumbers.contains(num)) {
                    lottoNumbers.add(num);
                }
            }
            return lottoNumbers;
        }
    
}