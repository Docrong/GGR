package com.boco.eoms.autosheet.servletoper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.common.tree.ModuleTree;
import com.boco.eoms.db.util.ConnectionPool;

public class SheetModelSelect {
	ConnectionPool pool = ConnectionPool.getInstance();

	Connection con = null;

	public SheetModelSelect() {

	}

	public List Tree(String MouldeId) {
		List TreeName = new ArrayList();
		ModuleTree alltree = new ModuleTree();
		String AllTree = alltree.strMTree(MouldeId);
		String[] ModelTemp = AllTree.split(";");
		String[] TreeInfo = null;
		for (int i = 0; i < ModelTemp.length - 1; i++) {
			String Spaces = "";
			String ModelInfo = ModelTemp[i].substring(ModelTemp[i]
					.indexOf("\"") + 1, ModelTemp[i].length() - 1);
			// System.out.println(ModelInfo);
			String Temp = "";

			while (ModelInfo.indexOf("|") != -1) {
				Temp = Temp + ModelInfo.substring(0, ModelInfo.indexOf("|"))
						+ "_";
				ModelInfo = ModelInfo.substring(ModelInfo.indexOf("|") + 1);
			}
			Temp = Temp + ModelInfo;
			TreeInfo = Temp.split("_");
			int Level = TreeInfo[0].length() / 2;

			for (int j = 1; j < Level; j++) {
				if (Level > 1) {
					Spaces = ".." + Spaces;
				}
			}
			String Node = TreeInfo[0] + "_" + Level + "_" + Spaces
					+ TreeInfo[2];
			// System.out.println(Node);
			TreeName.add(Node);
		}
		return TreeName;
	}

}
