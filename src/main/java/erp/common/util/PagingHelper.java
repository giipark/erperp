package erp.common.util;

import java.util.HashMap;
import java.util.List;

/**
 * 페이징 클래스
 *
 */
public class PagingHelper {
	
	/** 한 페이지 글 갯수 */
	private int pageSize;
	
	/** 페이지 그룹 갯수 */
	private int pageGroup;
	
	/** 현재 페이지 번호 */
	private int pageNumber;
	
	/** 시작 페이지 */
	private int startPage;
	
	/** 종료 페이지 */
	private int endPage;
	
	/** 총 페이지 수 */
	private int totalPageCount;
	
	
	private int startRow;
	private int endRow;
	/**
	 * 기본 페이지/그룹 사이즈로 생성
	 */
	public PagingHelper() {
		pageSize = 10;
		pageGroup = 10;
	}
	
	/**
	 * 페이지 페이지/그룹 사이즈 변경하여 생성
	 * @param pageSize
	 * @param pageGroup
	 */
	public PagingHelper(int pageSize, int pageGroup) {
		this.pageSize = pageSize;
		this.pageGroup = pageGroup;
	}
	
	/**
	 * 파라메터로 받은 검색용 맵에 페이징용 변수를 추가하여 리턴
	 * @param pagingParam
	 * @param totalCount
	 * @param aPageNumber
	 * @return
	 */
	public HashMap<String, Object> getPagingParam(HashMap<String, Object> pagingParam, int totalCount) {
		
		//페이지 변수가 널이거나 공백이면 1로 변경
		if (pagingParam.get("page") == null || pagingParam.get("page").equals("")) {
			pagingParam.put("page", "1");
		}
		
		pageNumber = Integer.parseInt((String)pagingParam.get("page"));
		
		//페이지 번호가 0보다 작으면 1로 고정
		if (pageNumber <= 0) {
			pageNumber = 1;
		}
		
		int totalPageCount = totalCount / pageSize;
		
		//페이지가 0으로 나누어 떨어지지 않으면 총 페이지 수에 1을 더한다
		if (totalCount % pageSize != 0) {
			totalPageCount++;
		}
		
		this.totalPageCount = totalPageCount;
		
		//페이지 번호가 총 페이지 수보다 크면 마지막 페이지 번호로 변경
		if (totalPageCount < pageNumber) {
			pageNumber = totalPageCount;
		}
		
		this.startPage = (pageNumber - 1) / pageGroup * pageGroup + 1;
		int endPage = startPage + (pageGroup - 1);
		
		//마지막 페이지가 총 페이지 수보다 크면 마지막 페이지 번호로 변경
		if (endPage > totalPageCount) {
			endPage = totalPageCount;
		}
		
		this.endPage = endPage;
		
		endRow = pageSize * pageNumber;
		startRow = endRow - pageSize;
		
		//시작번호가  0보다 작으면 0으로 변경
		if (startRow < 0) {
			startRow = 0;
		}
		
		pagingParam.put("startRow", startRow);
		pagingParam.put("endRow", pageSize);
		return pagingParam;
	}
	
	/**
	 * 리스트에 네비게이터 변수 추가
	 * @return
	 */
	public HashMap<String, Object> getPageList(List<HashMap<String, Object>> list, String functionName, int totalCnt) {
		
		int prevPage = pageNumber - 1;
		int nextPage = pageNumber + 1;
		
		//이전 페이지가 1보다 작으면 1로 설정
		if (prevPage < 1) {
			prevPage = 1;
		}
		
		//다음페이지가 마지막 페이지보다 높으면 마지막 페이지 번호로 설정
		if (nextPage > totalPageCount) {
			nextPage = totalPageCount;
		}
		
		int currentGroup = (int)Math.ceil((double)pageNumber / this.pageGroup);
		
		//이전 다음 블록
		int prevGroup = ((currentGroup - 2) * this.pageGroup) + this.pageGroup;
		int nextGroup = (currentGroup * this.pageGroup) + 1;
		
		if (prevGroup < this.pageGroup) {
			prevGroup = 1;
		}
		
		if (nextGroup > totalPageCount) {
			nextGroup = totalPageCount;
		}
		
		String navString = "<ul><li><a href=\"javascript:" + functionName + "(" + prevGroup + ");\" class=\"arrow bba\"></a></li>";
		navString += "<li><a href=\"javascript:" + functionName + "(" + prevPage + ");\" class=\"arrow ba\"></a></li>";
		
		for (int page = startPage; page <= endPage; page++) {
			if (page == pageNumber) {
				navString += "<li><a href=\"#\" class=\"active\">" + page + "</a></li>";
			} else {
				navString += "<li><a href=\"javascript:" + functionName + "(" + page + ");\">" + page + "</a></li>";
			}
		}
		
		navString += "<li><a href=\"javascript:" + functionName + "(" + nextPage + ");\" class=\"arrow na\"></a></li>";
		navString += "<li><a href=\"javascript:" + functionName + "(" + nextGroup + ");\" class=\"arrow nna\"></a></li><ul>";
		
		HashMap<String, Object> pagingList = new HashMap<String, Object>();
		
		pagingList.put("list", list);
		pagingList.put("pageNav", navString);
		pagingList.put("totalCnt", totalCnt);
		return pagingList;
	}
	
	/**
	 * 리스트에 네비게이터 변수 추가
	 * 수업리스트에서 사용. 2018-04-02 이승호 추가
	 */
	public HashMap<String, Object> getPageCnt(int totalCnt) {
		
		int prevPage = pageNumber - 1;
		int nextPage = pageNumber + 1;
		
		int currentGroup = (int)Math.ceil((double)pageNumber / this.pageGroup);
		
		//이전 다음 블록
		int prevGroup = ((currentGroup - 2) * this.pageGroup) + this.pageGroup;
		int nextGroup = (currentGroup * this.pageGroup) + 1;
		
		if (prevGroup < this.pageGroup) {
			prevGroup = 1;
		}
		
		if (nextGroup > totalPageCount) {
			nextGroup = totalPageCount;
		}
		HashMap<String, Object> pagingList = new HashMap<String, Object>();
		pagingList.put("preCnt", prevPage);
		pagingList.put("nextCnt", nextPage);
		pagingList.put("totalCnt", totalPageCount);
		return pagingList;
	}
	
	/**
	 * 리스트에 네비게이터 변수 추가
	 * @return
	 */
	public HashMap<String, Object> getMobilePageList(List<HashMap<String, Object>> list, String functionName, int totalCnt) {
		
		int prevPage = pageNumber - 1;
		int nextPage = pageNumber + 1;
		
		//이전 페이지가 1보다 작으면 1로 설정
		if (prevPage < 1) {
			prevPage = 1;
		}
		
		//다음페이지가 마지막 페이지보다 높으면 마지막 페이지 번호로 설정
		if (nextPage > totalPageCount) {
			nextPage = totalPageCount;
		}
		
		int currentGroup = (int)Math.ceil((double)pageNumber / this.pageGroup);
		
		//이전 다음 블록
		int prevGroup = ((currentGroup - 2) * this.pageGroup) + this.pageGroup;
		int nextGroup = (currentGroup * this.pageGroup) + 1;
		
		if (prevGroup < this.pageGroup) {
			prevGroup = 1;
		}
		
		if (nextGroup > totalPageCount) {
			nextGroup = totalPageCount;
		}
		
		String navString = "<div class=\"btn_wrap\">";
		for (int page = startPage; page <= endPage; page++) {
			if (page == pageNumber) {
				if (page-1 > 0) {
					navString += "<button class=\"bgo\" onclick=\"javascript:"+functionName+"(" + prevGroup + ");\"></button>";
				}
				navString += "<div class=\"swrap\"><span class=\"num_r\">"+page+"</span><span>/</span><span class=\"num\">"+endPage+"</span></div>";
				if (page+1 <= endPage) {
					navString += "<button class=\"fgo\" onclick=\"javascript:"+functionName+"(" + nextGroup + ");\"></button>";
				}
			}
		}
		navString += "</div>";
		
		HashMap<String, Object> pagingList = new HashMap<String, Object>();
		
		pagingList.put("list", list);
		pagingList.put("pageNav", navString);
		pagingList.put("totalCnt", totalCnt);
		return pagingList;
	}
	
	/**
	 * 리스트에 네비게이터 변수 추가
	 * @return
	 */
	public HashMap<String, Object> getUportPageList(List<HashMap<String, Object>> list, String functionName, int totalCnt) {
		
		int prevPage = pageNumber - 1;
		int nextPage = pageNumber + 1;
		
		//이전 페이지가 1보다 작으면 1로 설정
		if (prevPage < 1) {
			prevPage = 1;
		}
		
		//다음페이지가 마지막 페이지보다 높으면 마지막 페이지 번호로 설정
		if (nextPage > totalPageCount) {
			nextPage = totalPageCount;
		}
		
		int currentGroup = (int)Math.ceil((double)pageNumber / this.pageGroup);
		
		//이전 다음 블록
		int prevGroup = ((currentGroup - 2) * this.pageGroup) + this.pageGroup;
		int nextGroup = (currentGroup * this.pageGroup) + 1;
		
		if (prevGroup < this.pageGroup) {
			prevGroup = 1;
		}
		
		if (nextGroup > totalPageCount) {
			nextGroup = totalPageCount;
		}
		
		String navString = "<button class=\"start\" onClick=\""+ functionName + "(" + prevGroup + ");\"> ";
		navString += "<button class=\"prev\"onClick=\""+ functionName + "(" + prevPage + ");\"></button><ul class=\"paginate-list f-l overflow-hidden\">";
		
		for (int page = startPage; page <= endPage; page++) {
			if (page == pageNumber) {
				navString += "<li class=\"page-num active\"><a href=\"#\">" + page + "</a></li>";
			} else {
				navString += "<li class=\"page-num\"><a href=\"javascript:" + functionName + "(" + page + ");\">" + page + "</a></li>";
			}
		}
		
		navString += "<button class=\"next\" onClick=\"" + functionName + "(" + nextPage + ");\"></button>";
		navString += "<button class=\"end\" onClick=\"" + functionName + "(" + nextGroup + ");\"></button>";
		
		HashMap<String, Object> pagingList = new HashMap<String, Object>();
		
		pagingList.put("list", list);
		pagingList.put("pageNav", navString);
		pagingList.put("totalCnt", totalCnt);
		return pagingList;
	}
	
	public HashMap<String, Object> getUportMainPageList(List<HashMap<String, Object>> list, String functionName, int totalCnt) {
		
		int prevPage = pageNumber - 1;
		int nextPage = pageNumber + 1;
		
		//이전 페이지가 1보다 작으면 1로 설정
		if (prevPage < 1) {
			prevPage = 1;
		}
		
		//다음페이지가 마지막 페이지보다 높으면 마지막 페이지 번호로 설정
		if (nextPage > totalPageCount) {
			nextPage = totalPageCount;
		}
		
		int currentGroup = (int)Math.ceil((double)pageNumber / this.pageGroup);
		
		//이전 다음 블록
		int prevGroup = ((currentGroup - 2) * this.pageGroup) + this.pageGroup;
		int nextGroup = (currentGroup * this.pageGroup) + 1;
		
		if (prevGroup < this.pageGroup) {
			prevGroup = 1;
		}
		
		if (nextGroup > totalPageCount) {
			nextGroup = totalPageCount;
		}
		
		String navString = "<button class=\"prev\"onClick=\""+ functionName + "(" + prevPage + ");\"></button><ul class=\"paginate-list f-l overflow-hidden\">";
		
		for (int page = startPage; page <= endPage; page++) {
			if (page == pageNumber) {
				navString += "<li class=\"page-num active\"><a href=\"#\">" + page + "</a></li>";
			} else {
				navString += "<li class=\"page-num\"><a href=\"javascript:" + functionName + "(" + page + ");\">" + page + "</a></li>";
			}
		}
		
		navString += "<button class=\"next\" onClick=\"" + functionName + "(" + nextPage + ");\"></button>";
		
		HashMap<String, Object> pagingList = new HashMap<String, Object>();
		
		pagingList.put("list", list);
		pagingList.put("pageNav", navString);
		pagingList.put("totalCnt", totalCnt);
		return pagingList;
	}
}
