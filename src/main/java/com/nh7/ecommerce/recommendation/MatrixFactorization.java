package com.nh7.ecommerce.recommendation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatrixFactorization {
    final double rating=0.01; // Learning rate 0.01
    final double lambda=0.0011; //Lambda 0.0011
    final int iter=5000;		//iter 5000
    public void train(double [][]normalizedMat,double [][]matX,double [][]matW) {
        int m = matX.length;
        int n= matW[0].length;
        int d=matX[0].length;
        for(int h=0;h<iter;h++) {
            for(int i=0;i<m;i++) {
                for(int j=0;j<n;j++) {
                    if(normalizedMat[i][j] !=0) {
                        double error=normalizedMat[i][j];
                        for(int k=0;k<d;k++) {
                            error-=matX[i][k]*matW[k][j];
                        }
                        for(int k=0;k<d;k++) {
                            matW[k][j]+=rating*(d*error*matX[i][k]-lambda*matW[k][j]);
                            matX[i][k]+=rating*(d*error*matW[k][j]-lambda*matX[i][k]);
                        }
                    }
                }
            }
            double loss=0;
            for(int i=0;i<m;i++) {
                for(int j=0;j<n;j++) {
                    if(normalizedMat[i][j] !=0) {
                        double spec=0;
                        for(int k=0;k<d;k++) {
                            spec+=matX[i][k]*matW[k][j];
                        }
                        loss+=Math.pow(normalizedMat[i][j]-spec, 2);
                        for(int k=0;k<d;k++) {
                            loss+=(lambda/2)*(Math.pow(matX[i][k], 2)+Math.pow(matW[k][j], 2));
                        }
                    }
                }
            }
            if(loss<0.001) {
                break;
            }
            if(h%(iter/5)==0) {
                System.out.println("LOSS: "+ loss);
            }
            if(h==iter) {
                System.out.println("LOSS: "+ loss);
            }
        }
    }
}
