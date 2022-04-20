<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
								<div class="form-group">
								
											 <div class="col-sm-4">
												<label class="col-sm-4 control-label">자재 코드:</label>
												<div class="col-sm-8" id="list_0_div">				
													<input type="text" title="자재 코드" name="list[0].mcode" id="0" class="form-control form-tt" 
													onkeyup="getByMcodeAutoCompleate(this)"   autocomplete="off">		
								<div class="tt-menu" id="tt-menu_0" onmouseleave="autoCompleteHide(this)">
									<div class="tt-dataset tt-dataset-states" id="tt-dataset_0" >										
									</div>
								</div>																										
												</div>
																					
												
											 </div>
											 <div class="col-sm-5">
												<label class="col-sm-3 control-label">자재 명:</label>
												<div class="col-sm-9">
													<input type="text" title="자재 명" name="list[0].materialName" id="list_0_materialName" class="form-control">
												</div>
											 </div>
											<div class="col-sm-3">
												<label class="col-sm-5 control-label">자재 수량:</label>
												<div class="col-sm-7">
													<input type="text" title="자재 수량" name="list[0].materialQuantity" id="list_0_materialQuantity" 
													numberonly="true" style = "text-align:right;"
													class="form-control">
												</div>
											 </div>										
										</div>
							
							