leavemanagement
		.controller(
				'employeeController',
				function($rootScope, $routeParams, $scope, $location, $route,
						$timeout, $resource, $http, employeeService) {
					$scope.step = 1;
					$scope.employee = {
						"degreeInfo" : [ {
							"degree" : "",
							"university" : "",
							"degreeYear" : "",
							"percentageMarks" : ""
						} ],
						"secondaryInfo" : [ {
							"secondaryName" : "Secondary",
							"boardName" : "",
							"secondaryYear" : "",
							"secondaryPercentage" : ""
						}, {
							"secondaryName" : "High Secondary",
							"boardName" : "",
							"secondaryYear" : "",
							"secondaryPercentage" : ""
						} ]
					};

					$scope.status = '';
					$scope.loading = false;
					$scope.yearList = [];
					$scope.monthLisdegreet = [ 'JANUARY', 'FEBRUARY', 'MARCH',
							'APRIL', 'MAY', 'JUNE', 'JULY', 'AUGUST',
							'SEPTEMBER', 'OCTOBER', 'NOVEMBER', 'DECEMBER' ];
					$scope.dayList = [];
					$scope.questionList = [];
					$rootScope.empId = 0;
					$scope.selected = '';
					$scope.isUnique = false;
					$scope.imgFlag = false;
					$scope.buttonDisable = false;
					// $scope.init = function() {
					// alert("1111");
					// if(angular.isUndefined())
					// if ($route.current.templateUrl ==
					// "./resources/leave-management-angular-src/partials/employee_update_info.html")
					// {
					// alert("---");
					// alert("test1 : " + $routeParams.id);
					// $scope.getEmployeeUpdateInfo($routeParams.id);
					// }
					// };
					// $scope.init();

					// getting security Question List
//					var qsnData = employeeService.getSecurityQuestion();
//					qsnData.$promise.then(function(data) {
//						$scope.questionList = data.securityQuestions;
//						console.log($scope.questionList);
//					});
					// dynamic university List adding
					$scope.eduObj = {
						"degree" : "",
						"university" : "",
						"degreeYear" : "",
						"percentageMarks" : ""
					}
					$scope.errorArray = [ {
						"boardNameError" : false,
						"secYearError" : false,
						"secPercentageError" : false
					} ];
					$scope.degreeInfo = [ angular.copy($scope.eduObj) ];
					$scope.addNewChoice = function(event) {
						event.preventDefault();
						var newItemNo = $scope.degreeInfo.length + 1;
						$scope.employee.degreeInfo.push(angular
								.copy($scope.eduObj));
					};

					$scope.removeChoice = function(val, event) {
						event.preventDefault();
						if ($scope.employee.degreeInfo.length > 1) {
							var lastItem = $scope.employee.degreeInfo.length - 1;
							$scope.employee.degreeInfo.splice(val, 1);
						}

					};

					// university Auto complete
					$scope.universityList = {};
					$scope.getUniversityName = function(val) {
						var success = employeeService.getUniversityName(val);
						success.$promise.then(function(data) {
							$scope.universityList = data.universityName;
						});

					};
					// Board Name Auto Complete
					$scope.boardList = {};
					$scope.getBoardName = function(val) {
						var success = employeeService.getBoardName(val);
						success.$promise.then(function(data) {
							$scope.boardList = data.boardName;
						});

					};
					// check Unique Email
					$scope.isUniqueEmail = function(email) {
						var temp = employeeService.isUniqueEmail(email);
						temp.$promise.then(function(data) {
							if (data.isUniqueEmail) {
								$scope.isUnique = false;
							} else {
								$scope.isUnique = true;
							}

						});
					};
					// check Unique Mobile
					$scope.isUniqueMob = false;
					$scope.isUniqueMobile = function(mobileNumber) {
						var temp = employeeService.isUniqueMobile(mobileNumber);
						temp.$promise.then(function(data) {
							if (data.isUniqueMobileNumber) {
								$scope.isUniqueMob = false;
							} else {
								$scope.isUniqueMob = true;
							}

						});
					};
					// Getting Year List from 1990 to current year
					$scope.date = (new Date()).getFullYear();
					for (var i = $scope.date; i > 1950; i--) {
						$scope.yearList.push(i);
					}

					// Employee Profile Update Step
					$scope.isValidDay = false;
					$scope.nextStep = function(employee, isValid, event) {
						$scope.isValidDay = false;
						event.preventDefault();
						if (isValid) {
							if ($scope.step == 1
									&& (employee.birthdayYear % 4 == 0
											&& employee.birthdayMonth == 02 && employee.birthDay == 29)) {
								$scope.isValidDay = true;
							} else {
								$scope.step = $scope.step + 1;
							}
						} else {
							alert("test3");
						}
					}

					$scope.prevStep = function(event) {
						event.preventDefault();
						$scope.isValidDay = false;
						$scope.step = $scope.step - 1;
					}
					// Employee Information with Pagination
					$scope.limit = 30;
					$scope.offset = 0;
					$scope.totalPages = 0;
					$scope.currentPage = 0;
					$scope.Math = window.Math;
					$scope.empInfoList = null;

					$scope.employeeList = function(isFirstSearched) {
						var obj = {
							"limit" : $scope.limit,
							"offset" : $scope.limit * $scope.currentPage,
							"isFirstSearched" : isFirstSearched
						};
						var temp = employeeService.employeeList(obj);
						temp.$promise.then(function(data) {
							if (data.empList.length > 0) {
								$scope.empInfoList = data.empList;
								$scope.currentPage = 0;
								$scope.totalPages = Math
										.ceil(data.totalResultCount
												/ $scope.limit);
							} else {
								$scope.totalPages = 0;
							}

						});

					}
					// Pagination for employee Info
					$scope.paginationEmployeeList = function(isFirstSearched,
							paginationDirectionFlag) {
						if (paginationDirectionFlag) {
							$scope.currentPage++;
						} else {
							$scope.currentPage--;
						}
						var obj = {
							"limit" : $scope.limit,
							"offset" : $scope.limit * $scope.currentPage,
							"isFirstSearched" : false
						};
						var temp = employeeService.employeeList(obj);
						temp.$promise.then(function(data) {
							if (data.empList.length > 0) {
								$scope.empInfoList = data.empList;
								$scope.totalPages = Math
										.ceil(data.totalResultCount
												/ $scope.limit);
							} else {
								$scope.totalPages = 0;
							}

						});
					}
					// End of Pagination\\

					// Individual Employee Info Update
					$scope.updateEmployeeInfo = function(empInfo) {
						$rootScope.empId = angular.copy(empInfo);
						$location.path("/updateEmployeeInfo/" + empInfo.id);

					}

					// start image Uppload with crop
					$scope.myImage = '';
					$scope.myCroppedImage = '';
					$scope.cropImage = '';
					var handleFileSelect = function(evt) {
						$scope.imgFlag = false;
						$scope.buttonDisable = true;
						var file = '';
						file = evt.currentTarget.files[0];
						var reader = new FileReader();
						reader.onload = function(evt) {
							$scope.$apply(function($scope) {
								$scope.myImage = evt.target.result;
								$scope.cropImage = $scope.myImage;
								console.log($scope.cropImage);
							});
						};
						reader.readAsDataURL(file);

					};
					angular.element(document.querySelector('#filestyle-1')).on(
							'change', handleFileSelect);
					$scope.imgCrop = function(event) {
						$scope.cropImage = $scope.myCroppedImage;
						event.preventDefault();
						$scope.imgFlag = true;
						$scope.buttonDisable = false;
						// angular.element(document.querySelector('#filestyle-1')).val('');
					}
					/* End of Image Upload */

					// start save employee info
					$scope.submitForm = function(employee, isValid) {
						if (isValid) {
							alert("do you want to save");
							employee.profilePic = angular
									.copy($scope.cropImage);
							console.log("--");
							console.log(JSON.stringify(employee));
							var temp = employeeService
									.updateEmployeeInfo(employee);
							temp.$promise
									.then(function(data) {
										if (data.isUpdated) {
											var id = 1;
											$rootScope.employeeUpdateInfoData = employee;
											$location
													.path("/employeeUpdateInfo/"
															+ id);
										} else {

										}

									});
						} else {

						}
					};
					// get employee info based on id
					$scope.getEmployeeUpdateInfo = function(id) {
						alert("test");
					};
					$scope.errorArray = [];
					$scope.eduArray = {
						"boardNameError" : false,
						"secYearError" : false,
						"secPercentageError" : false,
					};
					$scope.degreeErrorArray = [];
					$scope.degreeArray = {
						"degreeError" : false,
						"universityError" : false,
						"degreeYearError" : false,
						"percentageMarksError" : false
					};
					// form validation with linear move
					$scope.checkStepValue = function(step, currentStep) {
						if (currentStep == 1) {

							if ($scope.empBasicInfoForm.$valid) {
								if ((employee.birthdayYear % 4 == 0
										&& employee.birthdayMonth == 02 && employee.birthDay == 29)) {
									$scope.isValidDay = true;
									$scope.step = currentStep;
									$scope.formOk = false;
								} else {
									$scope.step = step;
									$scope.formOk = true;
								}

							} else {
								$scope.step = currentStep;
								$scope.formOk = false;
							}
						} else if (currentStep == 2) {
							if ($scope.empAddressDetails.$valid) {
								$scope.step = step;

								$scope.formOk = true;
							} else {
								$scope.addClasses = currentStep;
								$scope.formOk = false;

							}

						}

						else if (currentStep == 3) {
							var formEduFlag = $scope.nextStepForm(
									$scope.employee, true, false);
							if (formEduFlag) {
								$scope.formOk = true;
								$scope.step = step;

							} else {
								$scope.step = currentStep;
								$scope.formOk = false;
							}
						} else if (currentStep == 4) {
							if ($scope.empUploadPic.$valid) {
								$scope.step = true;
								$scope.step = step;
							} else {
								$scope.step = currentStep;
								$scope.formOk = false;
							}
						}
					}
					$scope.nextStepForm = function(employee, isValid,
							formTestFlag) {
						var secFormOkCount = 0;
						var degreeFormOkCount = 0;
						secFormOk = false;
						var degreeFormOk = false;
						$scope.errorArray = [];
						$scope.degreeErrorArray = [];
						if (isValid) {
							for (var i = 0; i < employee.secondaryInfo.length; i++) {
								$scope.errorArray.push(angular
										.copy($scope.eduArray));
								if ((employee.secondaryInfo[i].boardName == '')
										&& (employee.secondaryInfo[i].secondaryYear == '' || employee.secondaryInfo[i].secondaryYear == null)
										&& (employee.secondaryInfo[i].secondaryPercentage == '' || employee.secondaryInfo[i].secondaryPercentage == null)) {

									$scope.errorArray[i].boardNameError = false;
									$scope.errorArray[i].secYearError = false;
									$scope.errorArray[i].secPercentageError = false;
									secFormOk = true;

								} else if ((employee.secondaryInfo[i].boardName != '')
										&& (employee.secondaryInfo[i].secondaryYear == '' || employee.secondaryInfo[i].secondaryYear == null)
										&& (employee.secondaryInfo[i].secondaryPercentage == '' || employee.secondaryInfo[i].secondaryPercentage == null)) {
									$scope.errorArray[i].boardNameError = false;
									$scope.errorArray[i].secYearError = true;
									$scope.errorArray[i].secPercentageError = true;
									secFormOkCount = secFormOkCount + 1;
									secFormOk = false;
									// $scope.employee.errorArray.push(angular.copy($scope.errorArray));

								} else if ((employee.secondaryInfo[i].secondaryYear != '' || employee.secondaryInfo[i].secondaryYear != null)
										&& employee.secondaryInfo[i].boardName == ''
										&& employee.secondaryInfo[i].secondaryPercentage == '') {
									$scope.errorArray[i].boardNameError = true;
									$scope.errorArray[i].secYearError = false;
									$scope.errorArray[i].secPercentageError = true;
									secFormOk = false;
									secFormOkCount = secFormOkCount + 1;
								} else if (employee.secondaryInfo[i].secondaryPercentage != ''
										&& (employee.secondaryInfo[i].secondaryYear == '' || employee.secondaryInfo[i].secondaryYear == null)
										&& employee.secondaryInfo[i].boardName == '') {
									$scope.errorArray[i].boardNameError = true;
									$scope.errorArray[i].secYearError = true;
									$scope.errorArray[i].secPercentageError = false;
									secFormOk = false;
									secFormOkCount = secFormOkCount + 1;
								} else if (employee.secondaryInfo[i].secondaryPercentage != ''
										&& (employee.secondaryInfo[i].secondaryYear != '' || employee.secondaryInfo[i].secondaryYear != null)
										&& employee.secondaryInfo[i].boardName == '') {
									$scope.errorArray[i].boardNameError = true;
									$scope.errorArray[i].secYearError = false;
									$scope.errorArray[i].secPercentageError = false;
									secFormOk = false;
									secFormOkCount = secFormOkCount + 1;
								} else if (employee.secondaryInfo[i].secondaryPercentage == ''
										&& (employee.secondaryInfo[i].secondaryYear != '' || employee.secondaryInfo[i].secondaryYear != null)
										&& employee.secondaryInfo[i].boardName != '') {
									$scope.errorArray[i].boardNameError = false;
									$scope.errorArray[i].secYearError = false;
									$scope.errorArray[i].secPercentageError = true;
									secFormOk = false;
									secFormOkCount = secFormOkCount + 1;
								} else if (employee.secondaryInfo[i].secondaryPercentage != ''
										&& (employee.secondaryInfo[i].secondaryYear == '' || employee.secondaryInfo[i].secondaryYear == null)
										&& employee.secondaryInfo[i].boardName != '') {
									$scope.errorArray[i].boardNameError = false;
									$scope.errorArray[i].secYearError = true;
									$scope.errorArray[i].secPercentageError = false;
									secFormOk = false;
									secFormOkCount = secFormOkCount + 1;
								} else {
									$scope.errorArray[i].boardNameError = false;
									$scope.errorArray[i].secYearError = false;
									$scope.errorArray[i].secPercentageError = false;
									secFormOk = true;
									// secFormOkCount = secFormOkCount+1;
								}
							}
							console.log("-------> "
									+ JSON.stringify($scope.degreeErrorArray));

							for (var j = 0; j < $scope.employee.degreeInfo.length; j++) {
								console.log("-------------"
										+ employee.degreeInfo[j].degreeYear);
								$scope.degreeErrorArray.push(angular
										.copy($scope.degreeArray));
								if (employee.degreeInfo[j].degree == ''
										&& employee.degreeInfo[j].university == ''
										&& (employee.degreeInfo[j].degreeYear == '' || employee.degreeInfo[j].degreeYear == null)
										&& employee.degreeInfo[j].percentageMarks == '') {
									$scope.degreeErrorArray[j].degreeError = false;
									$scope.degreeErrorArray[j].universityError = false;
									$scope.degreeErrorArray[j].degreeYearError = false;
									$scope.degreeErrorArray[j].percentageMarksError = false;
									degreeFormOk = true;
								}
								// single Field start
								else if (employee.degreeInfo[j].degree != ''
										&& employee.degreeInfo[j].university == ''
										&& (employee.degreeInfo[j].degreeYear == '' || employee.degreeInfo[j].degreeYear == null)
										&& employee.degreeInfo[j].percentageMarks == '') {
									$scope.degreeErrorArray[j].degreeError = false;
									$scope.degreeErrorArray[j].universityError = true;
									$scope.degreeErrorArray[j].degreeYearError = true;
									$scope.degreeErrorArray[j].percentageMarksError = true;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								} else if (employee.degreeInfo[j].degree == ''
										&& employee.degreeInfo[j].university != ''
										&& (employee.degreeInfo[j].degreeYear == '' || employee.degreeInfo[j].degreeYear == null)
										&& employee.degreeInfo[j].percentageMarks == '') {
									$scope.degreeErrorArray[j].degreeError = true;
									$scope.degreeErrorArray[j].universityError = false;
									$scope.degreeErrorArray[j].degreeYearError = true;
									$scope.degreeErrorArray[j].percentageMarksError = true;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								} else if ((employee.degreeInfo[j].degreeYear != '' || employee.degreeInfo[j].degreeYear != null)
										&& employee.degreeInfo[j].degree == ''
										&& employee.degreeInfo[j].university == ''
										&& employee.degreeInfo[j].percentageMarks == '') {
									$scope.degreeErrorArray[j].degreeError = true;
									$scope.degreeErrorArray[j].universityError = true;
									$scope.degreeErrorArray[j].degreeYearError = false;
									$scope.degreeErrorArray[j].percentageMarksError = true;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								} else if (employee.degreeInfo[j].degree == ''
										&& employee.degreeInfo[j].university == ''
										&& (employee.degreeInfo[j].degreeYear == '' || employee.degreeInfo[j].degreeYear == null)
										&& employee.degreeInfo[j].percentageMarks != '') {
									$scope.degreeErrorArray[j].degreeError = true;
									$scope.degreeErrorArray[j].universityError = true;
									$scope.degreeErrorArray[j].degreeYearError = true;
									$scope.degreeErrorArray[j].percentageMarksError = false;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								}
								// single field end
								// double field start for degree
								else if (employee.degreeInfo[j].degree != ''
										&& employee.degreeInfo[j].university != ''
										&& (employee.degreeInfo[j].degreeYear == '' || employee.degreeInfo[j].degreeYear == null)
										&& employee.degreeInfo[j].percentageMarks == '') {
									$scope.degreeErrorArray[j].degreeError = false;
									$scope.degreeErrorArray[j].universityError = false;
									$scope.degreeErrorArray[j].degreeYearError = true;
									$scope.degreeErrorArray[j].percentageMarksError = true;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								} else if (employee.degreeInfo[j].degree != ''
										&& employee.degreeInfo[j].university == ''
										&& (employee.degreeInfo[j].degreeYear != '' || employee.degreeInfo[j].degreeYear != null)
										&& employee.degreeInfo[j].percentageMarks == '') {
									$scope.degreeErrorArray[j].degreeError = false;
									$scope.degreeErrorArray[j].universityError = true;
									$scope.degreeErrorArray[j].degreeYearError = false;
									$scope.degreeErrorArray[j].percentageMarksError = true;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								} else if (employee.degreeInfo[j].degree != ''
										&& employee.degreeInfo[j].university == ''
										&& (employee.degreeInfo[j].degreeYear == '' || employee.degreeInfo[j].degreeYear == null)
										&& employee.degreeInfo[j].percentageMarks != '') {
									$scope.degreeErrorArray[j].degreeError = false;
									$scope.degreeErrorArray[j].universityError = true;
									$scope.degreeErrorArray[j].degreeYearError = true;
									$scope.degreeErrorArray[j].percentageMarksError = false;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								}
								// end of double field for degree
								// triple field start for degree
								else if (employee.degreeInfo[j].degree != ''
										&& employee.degreeInfo[j].university != ''
										&& (employee.degreeInfo[j].degreeYear != '' || employee.degreeInfo[j].degreeYear != null)
										&& employee.degreeInfo[j].percentageMarks == '') {

									$scope.degreeErrorArray[j].degreeError = false;
									$scope.degreeErrorArray[j].universityError = false;
									$scope.degreeErrorArray[j].degreeYearError = false;
									$scope.degreeErrorArray[j].percentageMarksError = true;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								} else if (employee.degreeInfo[j].degree != ''
										&& employee.degreeInfo[j].university == ''
										&& (employee.degreeInfo[j].degreeYear != '' || employee.degreeInfo[j].degreeYear != null)
										&& employee.degreeInfo[j].percentageMarks != '') {
									$scope.degreeErrorArray[j].degreeError = false;
									$scope.degreeErrorArray[j].universityError = true;
									$scope.degreeErrorArray[j].degreeYearError = false;
									$scope.degreeErrorArray[j].percentageMarksError = false;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								} else if (employee.degreeInfo[j].degree != ''
										&& employee.degreeInfo[j].university != ''
										&& (employee.degreeInfo[j].degreeYear == '' || employee.degreeInfo[j].degreeYear == null)
										&& employee.degreeInfo[j].percentageMarks != '') {
									$scope.degreeErrorArray[j].degreeError = false;
									$scope.degreeErrorArray[j].universityError = false;
									$scope.degreeErrorArray[j].degreeYearError = true;
									$scope.degreeErrorArray[j].percentageMarksError = false;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								}
								// END of triple field for degree
								// double field start for university
								else if (employee.degreeInfo[j].degree == ''
										&& employee.degreeInfo[j].university != ''
										&& (employee.degreeInfo[j].degreeYear != '' || employee.degreeInfo[j].degreeYear != null)
										&& employee.degreeInfo[j].percentageMarks == '') {
									$scope.degreeErrorArray[j].degreeError = true;
									$scope.degreeErrorArray[j].universityError = false;
									$scope.degreeErrorArray[j].degreeYearError = false;
									$scope.degreeErrorArray[j].percentageMarksError = true;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								} else if (employee.degreeInfo[j].degree == ''
										&& employee.degreeInfo[j].university != ''
										&& (employee.degreeInfo[j].degreeYear == '' || employee.degreeInfo[j].degreeYear == null)
										&& employee.degreeInfo[j].percentageMarks != '') {
									$scope.degreeErrorArray[j].degreeError = true;
									$scope.degreeErrorArray[j].universityError = false;
									$scope.degreeErrorArray[j].degreeYearError = true;
									$scope.degreeErrorArray[j].percentageMarksError = false;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								}
								// end of double field for University
								// triple field start for university
								else if (employee.degreeInfo[j].degree == ''
										&& employee.degreeInfo[j].university != ''
										&& (employee.degreeInfo[j].degreeYear != '' || employee.degreeInfo[j].degreeYear != null)
										&& employee.degreeInfo[j].percentageMarks != '') {
									$scope.degreeErrorArray[j].degreeError = true;
									$scope.degreeErrorArray[j].universityError = false;
									$scope.degreeErrorArray[j].degreeYearError = false;
									$scope.degreeErrorArray[j].percentageMarksError = false;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								}
								// END of triple field for degree
								// double field start for years
								else if (employee.degreeInfo[j].degree == ''
										&& employee.degreeInfo[j].university == ''
										&& (employee.degreeInfo[j].degreeYear != '' || employee.degreeInfo[j].degreeYear != null)
										&& employee.degreeInfo[j].percentageMarks != '') {
									$scope.degreeErrorArray[j].degreeError = true;
									$scope.degreeErrorArray[j].universityError = true;
									$scope.degreeErrorArray[j].degreeYearError = false;
									$scope.degreeErrorArray[j].percentageMarksError = false;
									degreeFormOk = false;
									degreeFormOkCount = degreeFormOkCount + 1;
								} else {
									$scope.degreeErrorArray[j].degreeError = false;
									$scope.degreeErrorArray[j].universityError = false;
									$scope.degreeErrorArray[j].degreeYearError = false;
									$scope.degreeErrorArray[j].percentageMarksError = false;
									// degreeFormOk = true;
								}
								// end of double field for University
							}
							if (degreeFormOkCount == 0 && secFormOkCount == 0) {
								if (formTestFlag) {
									$scope.step = $scope.step + 1;
								} else {
									return true;
								}
							} else {
								alert("9");
								return false;
							}
						} else {
						}
					}
					// By Vashitva

					console.log($routeParams.employeeId);
					$scope.calendarView = 'month';
					$scope.calendarDay = new Date();
					$scope.calendarTitle = "Vashitva";
					$scope.events = [];

					/*
					 * $scope.leaveRequestModal = { title : 'This is a really
					 * long event title', type : 'important', startsAt :
					 * moment().startOf('day').add(5, 'hours') .toDate(), endsAt :
					 * moment().startOf('day').add(19, 'hours') .toDate() };
					 */

					$scope.eventClicked = function(event) {
						alert("Test");
					};

					$scope.eventEdited = function(event) {
						// showModal('Edited', event);
						console.log(event);
					};

					$scope.eventDeleted = function(event) {
						showModal('Deleted', event);
					};

					$scope.toggle = function($event, field, event) {
						$event.preventDefault();
						$event.stopPropagation();
						event[field] = !event[field];
					};

					$scope.addNewEvent = function(calendarDate) {
						 alert(typeof(calendarDate));
						// alert(typeof(new Date()));
						// console.log(calendarDate);
						// console.log(calendarDate.getDate());
						// console.log(calendarDate.getMonth());
						// console.log(calendarDate.getFullYear());
						$scope.DateToday = new Date();
						var dateSelected = calendarDate.getFullYear()
								+ "-"
								+ ((calendarDate.getMonth() + 1).length > 1 ? (calendarDate
										.getMonth() + 1)
										: "0" + (calendarDate.getMonth() + 1))
								+ "-" + calendarDate.getDate();
						$scope.leaveRequestModal.startsAt = dateSelected;
						$scope.leaveRequestModal.endsAt = dateSelected;
						ngDialog
								.open({
									template : './resources/leave-management-angular-src/partials/leave_request_popup.html',
									scope : $scope,
									className : 'leaveRequest'
								});

					}
					$scope.ontimespanclick = function(calendarDate) {
					}

					$scope.sendLeaveRequest = function(formData, isValid) {
						if (isValid) {
							var temp = employeeService
									.sendLeaveRequest(formData);
							temp.$promise.then(function(data) {
								console.log(data);
								if (data.action == 'SHOW_CALANDER') {
									formData.startsAt = moment(
											formData.startsAt).toDate();
									formData.endsAt = moment(formData.endsAt)
											.toDate();
									formData.title += ", Leave Request Id - "
											+ data.LEAVE_REQUEST_ID;
									console.log(formData);
									$scope.events.push(angular.copy(formData));

									ngDialog.close();
								}
							}, function(error) {

							})
						}
					}
					$scope.startDateChanged = function(startDate, endDate) {
						console.log(startDate);
						console.log(endDate);
						var sDate = new Date(startDate);
						var eDate = new Date(endDate);
						console.log(eDate < sDate);
						if (eDate < sDate) {
							$scope.leaveRequestModal.endsAt = startDate;
						}

					}
					$scope.endDateChanged = function(startDate, endDate) {
						console.log(typeof (date))
						var sDate = new Date(startDate);
						var eDate = new Date(endDate);
						console.log(eDate < sDate);
						if (eDate < sDate) {
							$scope.leaveRequestModal.startsAt = endDate;
						}
					}
					// Ends Here
				});