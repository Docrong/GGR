<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:msxsl="urn:schemas-microsoft-com:xslt" xmlns:user="http://mycompany.com/mynamespace" xmlns:xslt="http://www.w3.org/TR/WD-xsl">
   <xsl:template name="creatSchem" match="/">
		<xsl:apply-templates/>
	</xsl:template>
   <xsl:template match="node[@appType='region']">
        <xsl:param name="isLastNode" select="0"/>
        <xsl:variable name="v_Last">
            <xsl:choose>
                <xsl:when test="last()=position()">
                <xsl:choose>
                <xsl:when test="$isLastNode='0'  and ../@nodeType!='root'">0</xsl:when>
                <xsl:otherwise>1</xsl:otherwise>
            </xsl:choose>
                </xsl:when>
                <xsl:otherwise>0</xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        <div nowrap="true" height="12">
            <xsl:for-each select="@*">
                <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
            </xsl:for-each>
            <xsl:attribute name="ID">xtree<xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="nodeId"><xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="state"><xsl:value-of select="//@states"/></xsl:attribute>
            <xsl:for-each select="ancestor-or-self::*[@nodeType!='root']">
                <xsl:if test="count(ancestor::node()) > 3">
                            <xsl:choose>
                                        <xsl:when test="count(parent::*/following-sibling::*)>0 ">
                                             <IMG topmargin="0">
                                           <xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/line.gif</xsl:attribute>
                                            </IMG>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <IMG topmargin="0">
                                           <xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/empty.gif</xsl:attribute>
                                            </IMG>
                                        </xsl:otherwise>
                             </xsl:choose>
              </xsl:if>
           </xsl:for-each>
            <!--<xsl:value-of select="$isLastNode"/>-->
            <xsl:choose>
				<xsl:when test="last()=position()">
                    <IMG topmargin="0">
                          <xsl:choose>
                            <xsl:when test="//@allExp='block'">
                               <xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/minusbottom.gif</xsl:attribute>
                            </xsl:when>
                            <xsl:otherwise>
                               <xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/plusbottom.gif</xsl:attribute>
                            </xsl:otherwise>
                        </xsl:choose>
                        <xsl:attribute name="onclick">ExpendNode('xtree<xsl:value-of select="@ID"/>','<xsl:value-of select="@ID"/>') </xsl:attribute>
                    </IMG>
				</xsl:when>
				<xsl:otherwise>

                    <IMG topmargin="0">
               <xsl:choose>
				<xsl:when test="//@allExp='block'">
                    <xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/minus.gif</xsl:attribute>
                </xsl:when>
				<xsl:otherwise>
                   <xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/plus.gif</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
                        <xsl:attribute name="onclick">ExpendNode('xtree<xsl:value-of select="@ID"/>','<xsl:value-of select="@ID"/>') </xsl:attribute>
                    </IMG>
				</xsl:otherwise>
			</xsl:choose>
					<IMG topmargin="0">
						<xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/folder.gif</xsl:attribute>
					</IMG>
			<span onmouseover="over(this)" onmousedown="down(this)" onmouseout="out(this)" onmouseup="this.className='clsCurrentHasFocus'">
				<xsl:attribute name="onclick">NodeInfo(this) </xsl:attribute>
				<xsl:choose>
					<xsl:when test="@title!=''">
						<xsl:attribute name="TITLE"><xsl:value-of select="@title"/></xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="TITLE"><xsl:value-of select="@name"/></xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:value-of select="@name"/>
			</span>
            <xsl:if test="//@region='true'">
				<input name="input_colls" height="12">
					<xsl:attribute name="type"><xsl:value-of select="//@muti"/></xsl:attribute>
					<xsl:attribute name="id"><xsl:value-of select="@region_id"/></xsl:attribute>
					<xsl:attribute name="app_name"><xsl:value-of select="@name"/></xsl:attribute>
					<xsl:attribute name="app_type">region</xsl:attribute>
					<xsl:attribute name="dept_ids"><xsl:for-each select="node[@appType='dept']"><xsl:value-of select="@dept_id"/>,</xsl:for-each>
					</xsl:attribute>
				</input>
			</xsl:if>
            <div height="12">
                <xsl:attribute name="id">node_ul_xtree<xsl:value-of select="@ID"/></xsl:attribute>
                <xsl:attribute name="style">display: <xsl:value-of select="//@allExp"/>;</xsl:attribute>
				<xsl:apply-templates  select="*">
                   <xsl:with-param name="isLastNode" select="$isLastNode+$v_Last"/>
                </xsl:apply-templates>
			</div>
		</div>
    </xsl:template>
    <xsl:template match="node[(@appType='dept')]">
        <xsl:param name="isLastNode" select="0"/>
        <xsl:variable name="v_Last">
            <xsl:choose>
                <xsl:when test="last()=position()">
                  <xsl:choose>
                <xsl:when test="$isLastNode='0' and ../@nodeType!='root'">0</xsl:when>
                <xsl:otherwise>1</xsl:otherwise>
            </xsl:choose>
                </xsl:when>
                <xsl:otherwise>0</xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        <xsl:if test="//@dept='true'">
        <div nowrap="true">
            <xsl:for-each select="@*">
                <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
            </xsl:for-each>
            <xsl:attribute name="ID">xtree<xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="nodeId"><xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="state">closed</xsl:attribute>
            <xsl:for-each select="ancestor-or-self::*[@nodeType!='root']">
                <xsl:if test="count(ancestor::node()) > 3">
                            <xsl:choose>
                                        <xsl:when test="count(parent::*/following-sibling::*)>0 ">
                                             <IMG topmargin="0">
                                           <xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/line.gif</xsl:attribute>
                                            </IMG>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <IMG topmargin="0">
                                           <xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/empty.gif</xsl:attribute>
                                            </IMG>
                                        </xsl:otherwise>
                             </xsl:choose>
              </xsl:if>
           </xsl:for-each>
            <!--<xsl:value-of select="$isLastNode"/>-->
            <xsl:choose>
				<xsl:when test="last()=position()">
                    <IMG topmargin="0">
				<xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/join.gif</xsl:attribute>
			</IMG>
				</xsl:when>
				<xsl:otherwise>
                    <IMG topmargin="0">
				<xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/joinbottom.gif</xsl:attribute>
			</IMG>
				</xsl:otherwise>
			</xsl:choose>
					<IMG topmargin="0">
						<xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/page.gif</xsl:attribute>
					</IMG>
			<span onmouseover="over(this)" onmousedown="down(this)" onmouseout="out(this)" onmouseup="this.className='clsCurrentHasFocus'">
				<xsl:attribute name="onclick">NodeInfo(this) </xsl:attribute>
				<xsl:choose>
					<xsl:when test="@title!=''">
						<xsl:attribute name="TITLE"><xsl:value-of select="@title"/></xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="TITLE"><xsl:value-of select="@name"/></xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:value-of select="@name"/>
			</span>
            <xsl:if test="//@dept='true'">
				<input name="input_colls" height="12">
					<xsl:attribute name="type"><xsl:value-of select="//@muti"/></xsl:attribute>
				    <xsl:attribute name="id"><xsl:value-of select="@dept_id"/></xsl:attribute>
					<xsl:attribute name="region_id"><xsl:value-of select="../@region_id"/></xsl:attribute>
					<xsl:attribute name="app_name"><xsl:value-of select="@name"/></xsl:attribute>
					<xsl:attribute name="app_type">dept</xsl:attribute>
			</input>
			</xsl:if>
            <div>
                <xsl:attribute name="id">leaf_ul_xtree<xsl:value-of select="@ID"/></xsl:attribute>
                <xsl:attribute name="style">display: none;</xsl:attribute>
				<xsl:apply-templates>
                     <xsl:with-param name="isLastNode" select="$isLastNode+$v_Last"/>
                </xsl:apply-templates>
			</div>
		</div>
        </xsl:if>
    </xsl:template>
    <xsl:template match="node[@nodeType='root']">
        <xsl:param name="isLastNode" select="0"/>
        <div ID="Root" STYLE="display:block;margin=0">
			<div>
                <xsl:for-each select="@*">
                    <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
                </xsl:for-each>
                <xsl:attribute name="ID">xtree<xsl:value-of select="@ID"/></xsl:attribute>
				<xsl:attribute name="nodeId"><xsl:value-of select="@ID"/></xsl:attribute>
				<xsl:attribute name="state"><xsl:value-of select="//@states"/></xsl:attribute>
				<IMG topmargin="0">
					<xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/folder.gif</xsl:attribute>
				</IMG>
				<span onmouseover="over(this)" onmousedown="down(this)" onmouseout="out(this)" onmouseup="this.className='clsCurrentHasFocus'">
					<xsl:choose>
						<xsl:when test="@title!=''">
							<xsl:attribute name="TITLE"><xsl:value-of select="@title"/></xsl:attribute>
						</xsl:when>
						<xsl:otherwise>
							<xsl:attribute name="TITLE"><xsl:value-of select="@name"/></xsl:attribute>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:attribute name="onclick">NodeInfo(this) </xsl:attribute>
					<IMG topmargin="0">
						<xsl:attribute name="SRC"><xsl:value-of select="//@treeImageBase"/>/img/folder.gif</xsl:attribute>
						<xsl:attribute name="style">display: none;</xsl:attribute>
					</IMG>
					<xsl:value-of select="@name"/>
				</span>
				<div>
                    <xsl:attribute name="id">root_ul_xtree<xsl:value-of select="@ID"/></xsl:attribute>
                    <xsl:attribute name="style">display: block;</xsl:attribute>
					<xsl:apply-templates  select="*">
                        <xsl:with-param name="isLastNode" select="0"/>
                    </xsl:apply-templates>
				</div>
			</div>
		</div>
	</xsl:template>
</xsl:stylesheet>
