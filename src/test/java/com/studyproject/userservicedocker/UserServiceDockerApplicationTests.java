package com.studyproject.userservicedocker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceDockerApplicationTests {

    @Test
    void printStarTreeTest() {
        System.out.println("======= Jenkins Test Stage: Star Tree =======");
        int rows = 5;

        for (int i = 1; i <= rows; i++) {
            // 공백 출력
            for (int j = rows; j > i; j--) {
                System.out.print(" ");
            }
            // 별 출력
            for (int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println("=============================================");
    }
}