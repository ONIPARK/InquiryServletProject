package main.java.utill;

public class Pagination {
	private int pageSize = 10; // 한 페이지에 보여질 글 수 
	private int rangeSize = 10; // 한 번에 보여질 페이지 번호 개수
	
	private int curPage; // 현재 페이지
	private int curRange; // 현재 블럭
	
	private int listCnt; // 전체 게시글 수
	private int pageCnt; // 전체 페이지 수
	private int rangeCnt; // 전체 블럭 수
	
	private int startPage; // 현재 블럭 시작 페이지 번호
	private int endPage; // 현재 블럭 끝 페이지 번호
	
	private int startIndex; // DB 조회 시작 인덱스 (OFFSET)
	private int prevPage; // 이전 블럭의 시작 페이지
	private int nextPage; // 다음 블럭의 시작 페이지
	
	public Pagination(int listCnt, int curPage) {
		this.listCnt = listCnt;
		this.curPage = curPage;
		
		// 전체 페이지 수
		this.pageCnt = (int) Math.ceil((double) listCnt / pageSize);
		
		// 현재 블럭
		this.curRange = (int) Math.floor((double) ((curPage - 1) / rangeSize) + 1);
		
		// 전체 블럭 수
		this.rangeCnt = (int) Math.ceil((double) pageCnt / rangeSize);
		
		// 현재 블럭의 시작 페이지, 끝 페이지
		this.startPage = ((curRange - 1) * rangeSize) + 1;
		this.endPage = curRange * rangeSize;
		
		// endPage가 pageCnt보다 크면 보정
		if (endPage > pageCnt) {
			this.endPage = pageCnt;
		}
		
		// DB에서 조회할 때 사용할 시작 인덱스
		this.startIndex = (curPage - 1) * pageSize;
		
		// 이전, 다음 페이지
		this.prevPage = curRange > 1 ? curRange - 1 * rangeSize : 1;
		this.nextPage = curRange < rangeCnt ? curRange * rangeSize + 1 : pageCnt;
	}
	
	// --- Getter들만 필요에 따라 추가 ---
    public int getStartIndex() {
        return startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public int getCurPage() {
        return curPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getPageCnt() {
        return pageCnt;
    }
	
}
