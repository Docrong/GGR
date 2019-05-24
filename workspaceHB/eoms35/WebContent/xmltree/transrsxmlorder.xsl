<?xml version="1.0" encoding="GB2312"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/TR/WD-xsl" xmlns:txsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:script language="JavaScript">
var imgPath;
var allExp="none";
var states="closed";
var icons="collapse.gif";
function SetPath(rootNode)
{
	imgPath=rootNode.getAttribute("treeImageBase");
	if (rootNode.getAttribute("allExp"))
	{
		allExp=rootNode.getAttribute("allExp");
		if (rootNode.getAttribute("allExp")==""||rootNode.getAttribute("allExp")=="block")
		{
			states="open";
			icons="expand.gif";
		}
	}
}
function GetPath()
{
	return imgPath;
}
function Exp()
{
	return allExp;
}
function state()
{
	return states;
}
function icon()
{
	return icons;
}
</xsl:script>
	<xsl:template name="creatSchem" match="/">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template name="treeNode" match="*">
		
		<xsl:apply-templates/>
		
	</xsl:template>
	<xsl:template name="treeNode" match="xTree">
		<xsl:eval>SetPath(this)</xsl:eval>
				<xsl:apply-templates/>
		</xsl:template>
	<xsl:template match="node[@nodeType='node']">
		<li>
				<xsl:for-each select="@*[nodeName() $ne$ 'ID']">
				<xsl:attribute><xsl:value-of/></xsl:attribute>
			</xsl:for-each>
			<xsl:attribute name="ID">xtree<xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="nodeId"><xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="state"><xsl:eval>state()</xsl:eval></xsl:attribute>
			<IMG WIDTH="16" topmargin="0">
				<xsl:attribute name="SRC"><xsl:eval>GetPath()</xsl:eval>/xtreeicon/<xsl:eval>icon()</xsl:eval></xsl:attribute>
				<xsl:attribute name="onclick">ExpendNode('xtree<xsl:value-of select="@ID"/>','<xsl:value-of select="@ID"/>') </xsl:attribute>
			</IMG>
			<xsl:choose>
				<xsl:when test="context()[@src $ne$ '']">
					<IMG WIDTH="14" topmargin="0">
						<xsl:attribute name="SRC"><xsl:value-of select="@src"/></xsl:attribute>
					</IMG>
				</xsl:when>
				<xsl:otherwise>
					<IMG WIDTH="14" topmargin="0">
						<xsl:attribute name="SRC"><xsl:eval>GetPath()</xsl:eval>/bs.gif</xsl:attribute>
					</IMG>
				</xsl:otherwise>
			</xsl:choose>
			<span onmouseover="over(this)" onmousedown="down(this)" onmouseout="out(this)" onmouseup="this.className='clsCurrentHasFocus'">
				<xsl:attribute name="onclick">NodeInfo(this) </xsl:attribute>
				<xsl:choose>
					<xsl:when test=".[@title!=''] ">
						<xsl:attribute name="TITLE"><xsl:value-of select="@title"/></xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="TITLE"><xsl:value-of select="@name"/></xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:value-of select="@name"/>
			</span>
			<ul>
				<xsl:attribute name="style">display: <xsl:eval>Exp()</xsl:eval>;</xsl:attribute>
				<xsl:apply-templates order-by="number(@order);@name" select="*"></xsl:apply-templates>
			</ul>
		</li>
	</xsl:template>
	<xsl:template match="node[@clickstate='onuse']">
		<li>
			
			<xsl:for-each select="@*[nodeName() $ne$ 'ID']">
				<xsl:attribute><xsl:value-of/></xsl:attribute>
			</xsl:for-each>
			<xsl:attribute name="ID">xtree<xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="nodeId"><xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="state"><xsl:eval>state()</xsl:eval></xsl:attribute>
			<IMG WIDTH="16" topmargin="0">
				<xsl:attribute name="SRC"><xsl:eval>GetPath()</xsl:eval>/xtreeicon/<xsl:eval>icon()</xsl:eval></xsl:attribute>
				<xsl:attribute name="onclick">ExpendNode('xtree<xsl:value-of select="@ID"/>','<xsl:value-of select="@ID"/>')</xsl:attribute>
			</IMG>
			<xsl:choose>
				<xsl:when test="context()[@src $ne$ '']">
					<IMG WIDTH="14" topmargin="0">
						<xsl:attribute name="SRC"><xsl:value-of select="@src"/></xsl:attribute>
					</IMG>
				</xsl:when>
				<xsl:otherwise>
					<IMG WIDTH="14" topmargin="0">
						<xsl:attribute name="SRC"><xsl:eval>GetPath()</xsl:eval>/bs.gif</xsl:attribute>
					</IMG>
				</xsl:otherwise>
			</xsl:choose>
			<span class="clsUnavailable" onmouseover="over(this)" onmousedown="down(this)" onmouseout="out(this)" onmouseup="this.className='clsCurrentHasFocus'">
				<xsl:attribute name="onclick">NodeInfo(this) </xsl:attribute>
				<xsl:choose>
					<xsl:when test=".[@title!=''] ">
						<xsl:attribute name="TITLE"><xsl:value-of select="@title"/></xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="TITLE"><xsl:value-of select="@name"/></xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:value-of select="@name"/>
			</span>
			<ul>
				<xsl:attribute name="style">display: <xsl:eval>Exp()</xsl:eval>;</xsl:attribute>
				<xsl:apply-templates order-by="number(@order);@name" select="*"></xsl:apply-templates>
			</ul>
		</li>
	</xsl:template>
	<xsl:template match="node[@nodeType='leaf']">
		<li>
			<xsl:for-each select="@*[nodeName() $ne$ 'ID']">
				<xsl:attribute><xsl:value-of/></xsl:attribute>
			</xsl:for-each>
			<xsl:attribute name="ID">xtree<xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="nodeId"><xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="state">closed</xsl:attribute>
			<IMG WIDTH="16">
				<xsl:attribute name="SRC"><xsl:eval>GetPath()</xsl:eval>/xtreeicon/leaf.gif</xsl:attribute>
			</IMG>
			<xsl:choose>
				<xsl:when test="context()[@src $ne$ '']">
					<IMG WIDTH="14">
						<xsl:attribute name="SRC"><xsl:value-of select="@src"/></xsl:attribute>
					</IMG>
				</xsl:when>
				<xsl:otherwise>
					<IMG WIDTH="14">
						<xsl:attribute name="SRC"><xsl:eval>GetPath()</xsl:eval>/bs.gif</xsl:attribute>
					</IMG>
				</xsl:otherwise>
			</xsl:choose>
			<span onmouseover="over(this)" onmousedown="down(this)" onmouseout="out(this)" onmouseup="this.className='clsCurrentHasFocus'">
				<xsl:attribute name="onclick">NodeInfo(this) </xsl:attribute>
				<xsl:choose>
					<xsl:when test=".[@title!=''] ">
						<xsl:attribute name="TITLE"><xsl:value-of select="@title"/></xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="TITLE"><xsl:value-of select="@name"/></xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:value-of select="@name"/>
			</span>
			<ul>
				<xsl:attribute name="style">display: none;</xsl:attribute>
				<xsl:apply-templates/>
			</ul>
		</li>
	</xsl:template>
	<xsl:template match="node[@apptype='template']">
		<li>
			<xsl:for-each select="@*[nodeName() $ne$ 'ID']">
				<xsl:attribute><xsl:value-of/></xsl:attribute>
			</xsl:for-each>
			<xsl:attribute name="ID">xtree<xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="nodeId"><xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="state">closed</xsl:attribute>
			<IMG WIDTH="16">
				<xsl:attribute name="SRC"><xsl:eval>GetPath()</xsl:eval>/xtreeicon/files.gif</xsl:attribute>
			</IMG>
			<span onmouseover="over(this)" onmousedown="down(this)" onmouseout="out(this)" onmouseup="this.className='clsCurrentHasFocus'">
				<xsl:attribute name="onclick">NodeInfo(this) </xsl:attribute>
				<xsl:choose>
					<xsl:when test=".[@title!=''] ">
						<xsl:attribute name="TITLE"><xsl:value-of select="@name"/></xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="TITLE"><xsl:value-of select="@name"/></xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:value-of select="@name"/>
			</span>
			<ul>
				<xsl:attribute name="style">display: none;</xsl:attribute>
				<xsl:apply-templates/>
			</ul>
		</li>
	</xsl:template>
	<xsl:template match="node[@nodeType='vnode']">
		<li>
			<xsl:for-each select="@*[nodeName() $ne$ 'ID']">
				<xsl:attribute><xsl:value-of/></xsl:attribute>
			</xsl:for-each>
			<xsl:attribute name="ID">xtree<xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="nodeId"><xsl:value-of select="@ID"/></xsl:attribute>
			<xsl:attribute name="state">load</xsl:attribute>
			<IMG WIDTH="16">
				<xsl:attribute name="SRC"><xsl:eval>GetPath()</xsl:eval>/xtreeicon/<xsl:eval>icon()</xsl:eval></xsl:attribute>
				<xsl:attribute name="onclick">ExpendNode('xtree<xsl:value-of select="@ID"/>','<xsl:value-of select="@ID"/>')</xsl:attribute>
			</IMG>
			<xsl:choose>
				<xsl:when test="context()[@src $ne$ '']">
					<IMG WIDTH="14">
						<xsl:attribute name="SRC"><xsl:value-of select="@src"/></xsl:attribute>
					</IMG>
				</xsl:when>
				<xsl:otherwise>
					<IMG WIDTH="14">
						<xsl:attribute name="SRC"><xsl:eval>GetPath()</xsl:eval>/bs.gif</xsl:attribute>
					</IMG>
				</xsl:otherwise>
			</xsl:choose>
			<span onmouseover="over(this)" onmousedown="down(this)" onmouseout="out(this)" onmouseup="this.className='clsCurrentHasFocus'">
				<xsl:attribute name="onclick">NodeInfo(this)</xsl:attribute>
				<xsl:choose>
					<xsl:when test=".[@title!=''] ">
						<xsl:attribute name="TITLE"><xsl:value-of select="@title"/></xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="TITLE"><xsl:value-of select="@name"/></xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:value-of select="@name"/>
			</span>
			<ul>
				<xsl:attribute name="style">display: none;</xsl:attribute>
				<xsl:apply-templates order-by="number(@order);@name" select="*"></xsl:apply-templates>
			</ul>
		</li>
	</xsl:template>
	<xsl:template match="node[@nodeType='root']">
		<ul ID="Root" STYLE="display:block;margin=3">
			<li>
				<xsl:for-each select="@*[nodeName() $ne$ 'ID']">
					<xsl:attribute><xsl:value-of/></xsl:attribute>
				</xsl:for-each>
				<xsl:attribute name="ID">xtree<xsl:value-of select="@ID"/></xsl:attribute>
				<xsl:attribute name="nodeId"><xsl:value-of select="@ID"/></xsl:attribute>
				<xsl:choose>
					<xsl:when test=".[@level $gt$ 0]">
						<xsl:attribute name="state">closed</xsl:attribute>
					</xsl:when>
					<xsl:otherwise>
						<xsl:attribute name="state">open</xsl:attribute>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:choose>
					<xsl:when test="context()[@src $ne$ '']">
						<IMG WIDTH="14">
							<xsl:attribute name="SRC"><xsl:value-of select="@src"/></xsl:attribute>
						</IMG>
					</xsl:when>
					<xsl:otherwise>
						<IMG WIDTH="14">
							<xsl:attribute name="SRC"><xsl:eval>GetPath()</xsl:eval>/bs.gif</xsl:attribute>
						</IMG>
					</xsl:otherwise>
				</xsl:choose>
				<span onmouseover="over(this)" onmousedown="down(this)" onmouseout="out(this)" onmouseup="this.className='clsCurrentHasFocus'">
					<xsl:choose>
						<xsl:when test=".[@title!=''] ">
							<xsl:attribute name="TITLE"><xsl:value-of select="@title"/></xsl:attribute>
						</xsl:when>
						<xsl:otherwise>
							<xsl:attribute name="TITLE"><xsl:value-of select="@name"/></xsl:attribute>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:attribute name="onclick">NodeInfo(this) </xsl:attribute>
					<IMG WIDTH="14">
						<xsl:attribute name="SRC">../images/bs.gif</xsl:attribute>
						<xsl:attribute name="style">display: none;</xsl:attribute>
					</IMG>
					<xsl:value-of select="@name"/>
				</span>
				<ul>
					<xsl:attribute name="style">display: block;</xsl:attribute>
					<xsl:apply-templates order-by="number(@order)" select="*"></xsl:apply-templates>
				</ul>
			</li>
		</ul>
	</xsl:template>
</xsl:stylesheet>
