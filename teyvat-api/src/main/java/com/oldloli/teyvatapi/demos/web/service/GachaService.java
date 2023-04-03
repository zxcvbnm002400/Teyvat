package com.oldloli.teyvatapi.demos.web.service;

import org.springframework.stereotype.Service;
import java.security.SecureRandom;

@Service
public class GachaService {
    public SecureRandom random = new SecureRandom();

    // 当抽卡次数n ∈ [1, 73]时，P(n) = 0.006
    // 当抽卡次数n ∈ [74, 90]时，p(n) = 0.006 + 0.06 * (n - 73)
    public double pullProbability(int n) {
        return n > 73 ? 6 + 60 * (n - 73) : 6;
    }

    public char simulate1Pull(int cnt, boolean isGuarantee) {
        if (random.nextInt(1000) >= pullProbability(cnt)) return 'N';
        else {
            if (isGuarantee) return 'A';
            else {
                if (random.nextBoolean()) return 'A';
                else return 'B';
            }
        }
    }

    public boolean simulateGacha(int num, int target, int cnt, boolean isGuarantee) {
        int cnt_A = 0;
        for (int i = 0; i < num; i++) {
            switch (simulate1Pull(cnt, isGuarantee)) {
                case 'A':
                    cnt_A++;
                    if (cnt_A == target) return true;
                    cnt = 0;
                    isGuarantee = false;
                    break;
                case 'B':
                    cnt = 0;
                    isGuarantee = true;
                    break;
                case 'N':
                    break;
            }
            cnt++;
        }
        return false;
    }

    public double gacha(int times, int num, int target, int cnt, boolean isGuarantee) {
        int cnt_success = 0;
        for (int i = 0; i < times; i++) {
            if (simulateGacha(num, target, cnt, isGuarantee)) cnt_success++;
        }
        return cnt_success / (double)times;
    }

}
