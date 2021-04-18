package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.model.ProductCardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductCardRepositoryImp implements ProductCardRepository{

    @Autowired
    private DataSource dataSource;

    @Override
    public List<ProductCardModel> findProductCardByCategoryId(long id) {

        String querry="select pr.id,pr.product_thumbnail,pr.product_price,po.post_title,sh.address,pr.quantity\n" +
                "from product pr\n" +
                "join post po on po.product_id=pr.id\n" +
                "join u on u.id=po.user_id\n" +
                "join shop sh on u.id=sh.user_id\n" +
                "join category ca on ca.id=pr.category_id\n" +
                "where ca.id="+id;
        List<ProductCardModel> productCardModels=null;

        try{
            Connection conn=dataSource.getConnection();
            PreparedStatement preStm=conn.prepareStatement(querry);
            ResultSet rs=preStm.executeQuery();
            productCardModels=new ArrayList<>();
            while(rs.next()){
                ProductCardModel productCardModel=new ProductCardModel();
                productCardModel.setId(rs.getLong("id"));
                productCardModel.setProductThumbnail(rs.getString("product_thumbnail"));
                productCardModel.setProductPrice(rs.getDouble("product_price"));
                productCardModel.setPostTitle(rs.getString("post_title"));
                productCardModel.setAddress(rs.getString("address"));
                productCardModel.setSoldQuantity(rs.getInt("quantity"));

                productCardModels.add(productCardModel);
            }
            conn.close();
            return productCardModels;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       return null;
    }
}
