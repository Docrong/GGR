<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template name="treeNode" match="infoTree">
		<xsl:apply-templates/>
	</xsl:template>
    <xsl:template match="node[@nodeType='node']">
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
			<div height="12">
                <xsl:attribute name="id">node_ul_xtree<xsl:value-of select="@ID"/></xsl:attribute>
                <xsl:attribute name="style">display: <xsl:value-of select="//@allExp"/>;</xsl:attribute>
				<xsl:apply-templates  select="*"/>
			</div>
		</div>
	</xsl:template>

	<xsl:template match="node[@nodeType='leaf']">
        <div nowrap="true">
            <xsl:for-each select="@*">
                <xsl:attribute name="{name(.)}"><xsl:value-of select="."/></xsl:attribute>
            </xsl:for-each>
            <xsl:attribute name="ID">xtree<xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="nodeId"><xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="state">closed</xsl:attribute>
            <xsl:for-each select="ancestor-or-self::*[parent::*/@nodeType!='root']">
                 <xsl:if test="count(ancestor::node()) > 3">
                            <xsl:choose>
                                        <xsl:when test="count(parent::*/following-sibling::*)>0">
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
			<div>
                <xsl:attribute name="id">leaf_ul_xtree<xsl:value-of select="@ID"/></xsl:attribute>
                <xsl:attribute name="style">display: none;</xsl:attribute>
				<xsl:apply-templates/>
			</div>
		</div>
	</xsl:template>
    <xsl:template match="node[@nodeType='root']">
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
					<xsl:apply-templates  select="*"/>
				</div>
			</div>
		</div>
	</xsl:template>
</xsl:stylesheet>
