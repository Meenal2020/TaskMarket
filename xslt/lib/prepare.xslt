<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version='2.0'
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:zenta="http://magwas.rulez.org/zenta">

	<xsl:param name="targetdir"/>

	<xsl:template match="source" mode="prepare"/>

	<xsl:template match="source[@type='github']" mode="prepare">
		<xsl:variable name="targetdir" select="concat('inputs/',@repo)"/>
		<xsl:value-of select="concat('rm -rf ',$targetdir,';git clone --branch ',@branch,' https://github.com/',@repo,'.git ',$targetdir,'&#10;')"/>
	</xsl:template>
	
	<xsl:template match="sources" mode="prepare">
		<xsl:apply-templates select="*" mode="prepare"/>
	</xsl:template>
	
</xsl:stylesheet>
