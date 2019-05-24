/**
 * @class eoms.sheet  工单相关函数
 * @version 0.1
 */
eoms.treeManager = function(){
	return {
		'dept' : {
			url: '/xtree.do?method=dept',
			rootId:-1,
			rootText:'部门'
		},
		'user' : {
			url: '/xtree.do?method=userFromDept',
			rootId:-1,
			rootText:'人员'
		}
	}
}();