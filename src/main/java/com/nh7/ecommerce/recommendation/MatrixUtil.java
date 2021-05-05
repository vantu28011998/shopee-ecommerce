package com.nh7.ecommerce.recommendation;

import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

@Component
public class MatrixUtil {
    public double[][] multiply(double [][]matA,double [][]matB) {
        int row=matA.length;
        int column=matB[0].length;
        double [][]matResult=new double[row][column];
        if(matA[0].length != matB.length) {
            throw new InvalidParameterException("Couldn't mutiply matrix because rows and columns invalid");
        }
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                for(int k=0;k<matA[0].length;k++) {
                    matResult[i][j]+=matA[i][k]*matB[k][j];
                }
            }
        }
        return matResult;
    }
    public double[][] transpose(double [][]mat) {
        int row=mat.length;
        int column=mat[0].length;
        double [][]resultMat=new double[row][column];
        for(int i=0;i<mat.length;i++) {
            for(int j=0;j<mat[0].length;j++) {
                resultMat[i][j]=mat[i][j];
            }
        }
        return resultMat;
    }
    public void initRandomMatrix(double [][]matrix) {
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[i].length;j++) {
                matrix[i][j]=Math.random()%9;
            }
        }
    }
    public void printMatrix(double  [][]matrix) {
        System.out.println("------ MATRIX ------");
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[i].length;j++) {
                System.out.print(String.format("%.2f", matrix[i][j])+ " ");
            }
            System.out.println();
        }
    }
    public double [][]getNormalizedMatrix(double [][]originalMat) {
        int row =originalMat.length;
        int column=originalMat[0].length;
        double[][] normalizedMatrix=new double[row][column];
        int []n=new int[column];
        double []sum=new double[column];
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                if(originalMat[i][j]>0){
                    ++n[j];
                    sum[j]+=originalMat[i][j];
                }
            }
        }

        for(int i=0;i<column;i++) {
            for(int j=0;j<row;j++) {
                if(originalMat[j][i]>0){
                    normalizedMatrix[j][i]=originalMat[j][i]-sum[i]/(double)n[i];
                }else {
                    normalizedMatrix[j][i]=0;
                }
            }
        }
        return normalizedMatrix;
    }
    public void updateOriginalMatrix(double [][]originalMat,double [][]normalizedMat) {
        int row =originalMat.length;
        int column=originalMat[0].length;
        int []n=new int[column];
        double []sum=new double[column];
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                if(originalMat[i][j]>0){
                    ++n[j];
                    sum[j]+=originalMat[i][j];
                }
            }
        }
        for(int i=0;i<row;i++) {
            for(int j=0;j<column;j++) {
                if(originalMat[i][j]<1){
                    originalMat[i][j]=normalizedMat[i][j]+sum[j]/n[j];
                }
            }
        }

    }
}
