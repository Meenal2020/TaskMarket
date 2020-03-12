<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:zenta="http://magwas.rulez.org/zenta"
	xmlns:zentatools="java:org.rulez.magwas.zentatools.XPathFunctions"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


    <xsl:function name="zenta:fullpackageP">
        <xsl:param name="package"/>
        <xsl:if test="$package">
            <xsl:variable name="parent" select="zenta:fullpackageP(zenta:neighbours($rich,$package,'contains,2')[@xsi:type='Package'])"/>
            <xsl:choose>
                <xsl:when test="$parent">
			<xsl:message select="'P'"/>
			<xsl:message select="$parent"/>
			<xsl:message select="$package/@name"/>
                    <xsl:value-of select="concat($parent, '.', $package/@name)"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="$package/@name"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:if>
    </xsl:function>

    <xsl:function name="zenta:fullpackage">
        <xsl:param name="service"/>
			<xsl:message select="'--'"/>
			<xsl:message select="$service/@name"/>
        <xsl:variable name="parent" select="zenta:neighbours($rich,$service,'contains,2')[@xsi:type='Package' or @xsi:type='Process Step']"/>
        <xsl:choose>
            <xsl:when test="$parent/@xsi:type='Package'">
			<xsl:message select="$parent/@name"/>
                <xsl:copy-of select="zenta:fullpackageP($parent[@xsi:type='Package'])"/>
            </xsl:when>
            <xsl:when test="$parent/@xsi:type='Process Step'">
                <xsl:copy-of select="zenta:fullpackage($parent)"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:variable name="implemented" select="zenta:neighbours($rich,$service,'is implemented by/implements,2')"/>
                <xsl:if test="not($implemented/@name)">
                	<xsl:value-of select="error(
		       			QName('http://magwas.rulez.org/zenta','zenta:nopackage'),
						concat('no package for: ',$service/@name))"/>
                </xsl:if>
                <xsl:copy-of select="zenta:fullpackage($implemented)"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:function>


</xsl:stylesheet>

