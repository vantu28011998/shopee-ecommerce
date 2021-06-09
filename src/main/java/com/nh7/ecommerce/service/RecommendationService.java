package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.ProductCardDto;
import com.nh7.ecommerce.dto.pageable.Pagination;
import com.nh7.ecommerce.dto.pageable.ResponsePageable;
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
    @Autowired
    private ProductService productService;
    public ResponsePageable<ProductCardDto> recommendFor(Long id, Pageable pageable){
        int limit=pageable.getPageSize();
        int page=pageable.getPageNumber();
        int offset=0;
        offset=page*limit;
        Long []productIdResult=new Long[limit];

        List<Long> userIds=userRepository.findAllId();
        List<Long> productIds=productRepository.findAllId();
        List<Rating> ratings= (List<Rating>)ratingRepository.findAll();
        int userSize=userIds.size();
        int productSize=productIds.size();
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
        RatedProductModel []ratedProductModels=new RatedProductModel[productSize];
        System.out.println(ratedProductModels.length);
        for(int i=0;i<productSize;i++){
            ratedProductModels[i]=new RatedProductModel(productIds.get(i),originMat[i][userPosition]);
        }
        for(int i=0;i<productSize-1;i++){
            for(int j=i+1;j<productSize;j++){
                if(ratedProductModels[i].getRating()<ratedProductModels[j].getRating()){
                    RatedProductModel temp=ratedProductModels[i];
                    ratedProductModels[i]=ratedProductModels[j];
                    ratedProductModels[j]=temp;
                }
            }
        }
        System.out.print("ID: ");
        for(int i=0;i<productSize;i++){
            System.out.print(" "+ratedProductModels[i].getId());
        }
        System.out.println();
        System.out.print("RATING: ");
        for(int i=0;i<productSize;i++){
            System.out.print(" "+ratedProductModels[i].getRating());
        }
        System.out.println("PRODUCT SIZE:" + productSize);
        if(offset+limit<=productSize){
            for(int i=0;i<limit;i++){
                productIdResult[i]=ratedProductModels[offset+i].getId();
                System.out.print(" "+ ratedProductModels[offset+i].getId());
            }
        }else System.out.println("OFFSET + LIMIT COULDN'T BE USED");
        System.out.println("LENGHTH = " + productIdResult.length);
        ResponsePageable<ProductCardDto> productCardDtoResponse = new ResponsePageable<>();
        Pagination pagination = new Pagination();
        pagination.setLimit(limit);
        pagination.setPage(page);
        Integer totalRow = productRepository.countAll();
        pagination.setTotalRow(totalRow);
        productCardDtoResponse.setPagination(pagination);
        productCardDtoResponse.setItems(productService.getProductsByIds(productIdResult));
        return productCardDtoResponse;
    }


}
