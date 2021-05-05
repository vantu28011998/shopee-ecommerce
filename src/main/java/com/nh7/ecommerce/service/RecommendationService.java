package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.Rating;
import com.nh7.ecommerce.model.RatedProductModel;
import com.nh7.ecommerce.recommendation.MatrixFactorization;
import com.nh7.ecommerce.recommendation.MatrixUtil;
import com.nh7.ecommerce.repository.ProductRepository;
import com.nh7.ecommerce.repository.RatingRepository;
import com.nh7.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class RecommendationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private MatrixUtil matrixUtil;
    @Autowired
    private MatrixFactorization matrixFactorization;
    public void recommendFor(Long id, Pageable pageable){

        List<Long> userIds=userRepository.findAllId();
        List<Long> productIds=productRepository.findAllId();
        List<Rating> ratings= (List<Rating>)ratingRepository.findAll();
        int userSize=userIds.size();
        int productSize=productIds.size();
        System.out.println("USER SIZE:"+userSize);
        System.out.println("ITEM SIZE:"+productSize);
        int k=2;
        double [][]originMat=new double[productSize][userSize];
        double [][]matX=new double[productSize][k];
        double [][]matW=new double[k][userSize];
        for(Rating rating : ratings){
            for(int i=0;i<productSize;i++){
                for(int j=0;j<userSize;j++){
                    if(rating.getUser().getId()==userIds.get(j)
                            && rating.getProduct().getId()==productIds.get(i)){
                        originMat[i][j]=rating.getRating();
                    }
                }
            }
        }
        System.out.println("============== BEFORE ===============");
        matrixUtil.printMatrix(originMat);
        matrixUtil.initRandomMatrix(matX);
        matrixUtil.initRandomMatrix(matW);
        double [][]normalizedMat=matrixUtil.getNormalizedMatrix(originMat);
        matrixFactorization.train(normalizedMat,matX,matW);
        double [][]normalMat=matrixUtil.multiply(matX,matW);
        matrixUtil.updateOriginalMatrix(originMat,normalMat);
        System.out.println("++++++++++++++ AFTER ++++++++++++++++");
        matrixUtil.printMatrix(originMat);

        int userPosition=userIds.indexOf(id);
        System.out.println("POSITION : "+ userPosition);
        double []recommenderRating=new double[productSize];
        for(int i=0;i<productSize;i++){
            recommenderRating[i]=originMat[i][userPosition];
        }

    }


}
