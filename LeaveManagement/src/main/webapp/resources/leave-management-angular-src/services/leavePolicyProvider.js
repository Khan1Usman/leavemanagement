leavemanagement.provider("leavePolicyProvider", function() {
	var leavePolicyData;
	this.setLeavePolicyData = function(data) {
		this.leavePolicyData = data;
	}
	this.$get = function() {
		return this.leavePolicyData;
	}

})