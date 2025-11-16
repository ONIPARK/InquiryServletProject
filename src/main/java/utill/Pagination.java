package main.java.util; 

public class Pagination {
    private int pageSize = 10;   // 1ページに表示する件数
    private int rangeSize = 10;  // 一度に表示するページ番号の数（ページングブロックのサイズ）

    private int curPage;   // 現在ページ
    private int curRange;  // 現在のブロック（1..rangeCnt）

    private int listCnt;   // 総件数
    private int pageCnt;   // 総ページ数
    private int rangeCnt;  // 総ブロック数

    private int startPage; // 現在ブロックの開始ページ番号
    private int endPage;   // 現在ブロックの終了ページ番号

    private int startIndex; // DB 取得開始インデックス（OFFSET）
    private int prevPage;   // 以前ブロックの開始ページ
    private int nextPage;   // 次ブロックの開始ページ

    public Pagination(int listCnt, int curPage) {
        this.listCnt = listCnt;
        this.curPage = curPage;

        // 総ページ数
        this.pageCnt = (int) Math.ceil((double) listCnt / pageSize);

        // 現在のブロック（1始まり）
        this.curRange = (int) Math.floor((double) ((curPage - 1) / rangeSize) + 1);

        // 総ブロック
        this.rangeCnt = (int) Math.ceil((double) pageCnt / rangeSize);

        // 現在ブロックの開始・終了ページ
        this.startPage = ((curRange - 1) * rangeSize) + 1;
        this.endPage = curRange * rangeSize;

        // endPage が pageCnt を超える場合、補正
        if (endPage > pageCnt) {
            this.endPage = pageCnt;
        }

        // DB 取得の開始 index（OFFSET）
        this.startIndex = (curPage - 1) * pageSize;

        // 前/次ブロックの開始ページ
        this.prevPage = curRange > 1 ? curRange - 1 * rangeSize : 1;
        this.nextPage = curRange < rangeCnt ? curRange * rangeSize + 1 : pageCnt;
    }

    // --- Getter ---
    public int getStartIndex() { return startIndex; }
    public int getPageSize() { return pageSize; }
    public int getStartPage() { return startPage; }
    public int getEndPage() { return endPage; }
    public int getCurPage() { return curPage; }
    public int getPrevPage() { return prevPage; }
    public int getNextPage() { return nextPage; }
    public int getPageCnt() { return pageCnt; }
}
