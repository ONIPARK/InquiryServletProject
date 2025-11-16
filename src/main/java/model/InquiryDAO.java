package main.java.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.InquiryDTO;
import mapper.InquiryMapper;
import mapper.InquirySql;

public class InquiryDAO {
    
    /**
     * 問い合わせ　記入
     */
    public static boolean insertInquiry(InquiryDTO dto) {

        try (
        	Connection conn = DBUtil.connect();
        	PreparedStatement pstmt = conn.prepareStatement(InquirySql.INSERT_INQUIRY)
        	){
        	// 여기서 매핑 처리
        	InquiryMapper.mapToPreparedStatement(pstmt, dto);
            	
            	return pstmt.executeUpdate() > 0;
            	
            } catch (Exception e) {
            System.out.println("問い合わせ　入力中エラー発生");
            //　データベース　解除
            e.printStackTrace();
            return false;
        } 
    }
    
    /**
     * リスト 投稿数COUNT
     */   
    public static int getTotalCount( ) {
    	try (
    		Connection conn = DBUtil.connect();
    		PreparedStatement pstmt = conn.prepareStatement(InquirySql.SELECT_INQUIRY_COUNT);
    		ResultSet rs = pstmt.executeQuery()){
			if (rs.next()) {
				// 첫 번째 컬럼(Count)을 가져와라
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return 0;
    }	
    /*
     * リスト　ページング処理
     * */
     public static List<InquiryDTO> getInquriesByPage(int startIndex, int pageSize) {
        List<InquiryDTO> list = new ArrayList<>();

        try (
            Connection conn = DBUtil.connect();
            PreparedStatement pstmt = conn.prepareStatement(InquirySql.SELECT_ALL_INQUIRIES);
            ) {
        	pstmt.setInt(1, pageSize);
        	pstmt.setInt(2, startIndex);
            
        	try (ResultSet rs = pstmt.executeQuery()){
				while (rs.next()) {
					InquiryDTO dto = new InquiryDTO();
					dto.setInquiryId(rs.getInt("inquiry_id"));
					dto.setSubject(rs.getString("subject"));
					dto.setName(rs.getString("name"));
					dto.setCreateDate(rs.getTimestamp("createdate"));
					dto.setHasReply(rs.getBoolean("has_reply"));
					
					list.add(dto);
				}
			} 
        } catch (Exception e) {
            System.out.println("問い合わせ一覧の取得中にエラーが発生しました");
            e.printStackTrace();
        }

        return list;
    }
    
    /*
     * 問い合わせのUPDATEページ(修正) 
     * */
    public static boolean updateInquiry(InquiryDTO dto) {
    	
    	try (
    		Connection conn = DBUtil.connect();
    		PreparedStatement pstmt = conn.prepareStatement(InquirySql.UPDATE_INQUIRY);
    	) {
    		InquiryMapper.bindUpdateParams(pstmt, dto);
    		return pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    
    
    
    /*　問い合わせの確認（詳細）ページ + 問い合わせUPDATE
     * 指定された問い合わせIDに該当するデータをデータベースかから取得し、
     * InquiryDTOオブジェクトとして返します。
     * */
    public static InquiryDTO getInquiryById(int inquiryId) {
    	InquiryDTO inquiry = null;
    	try (
    		Connection conn = DBUtil.connect();
    		PreparedStatement pstmt = conn.prepareStatement(InquirySql.SELECT_INQUIRY_BY_ID);
    		){
    		pstmt.setInt(1, inquiryId);
			ResultSet rs = pstmt.executeQuery();
    		if (rs.next()) {
    			// mapper 変更
    			inquiry = InquiryMapper.mapFromResultSetFull(rs);
                System.out.println("getInquiryById接続　成功：" + inquiry.getInquiryId());
    		}
    		rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	
    	return inquiry;
    	}
    /*
     * コメントを付けられるメソッド
     * */
    // 
    

    
    /*
     * 削除
     * */
    public static boolean deleteInquiryById(int inquiryId) {
    	try (
    		Connection conn = DBUtil.connect();
    		PreparedStatement pstmt = conn.prepareStatement(InquirySql.DELETE_INQUIRY);	
    		){
    		pstmt.setInt(1, inquiryId);
    		System.out.println("削除実行のinquiryId： " + inquiryId);
    		return pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
}

