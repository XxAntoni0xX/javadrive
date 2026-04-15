<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Reporte JavaDrive</title>
                <style>
                    body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 40px; background-color: #f0f2f5; }
                    h1 { color: #1a5f7a; text-align: center; border-bottom: 2px solid #1a5f7a; padding-bottom: 10px; }
                    h2 { color: #1a5f7a; margin-top: 30px; background: #e8f1f5; padding: 10px; border-radius: 5px; }
                    table { width: 100%; border-collapse: collapse; margin-bottom: 20px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); background: white; }
                    th { background-color: #1a5f7a; color: white; padding: 12px; text-align: left; }
                    td { border: 1px solid #ddd; padding: 12px; }
                    tr:hover { background-color: #f9f9f9; }
                    .tipo-coche { color: #d35400; font-weight: bold; }
                    .tipo-furgoneta { color: #27ae60; font-weight: bold; }
                </style>
            </head>
            <body>
                <h1>Sistema de Gestión JavaDrive - Informe General</h1>
                
                <h2>Inventario de Vehículos</h2>
                <table>
                    <tr>
                        <th>Tipo</th>
                        <th>Matrícula</th>
                        <th>Marca y Modelo</th>
                        <th>Detalles Técnicos</th>
                    </tr>
                    <xsl:for-each select="javadrive/flota/vehiculo">
                        <tr>
                            <td>
                                <xsl:attribute name="class">
                                    <xsl:choose>
                                        <xsl:when test="@tipo='Coche'">tipo-coche</xsl:when>
                                        <xsl:otherwise>tipo-furgoneta</xsl:otherwise>
                                    </xsl:choose>
                                </xsl:attribute>
                                <xsl:value-of select="@tipo"/>
                            </td>
                            <td><xsl:value-of select="matricula"/></td>
                            <td><xsl:value-of select="marca_modelo"/></td>
                            <td><xsl:value-of select="especificaciones"/></td>
                        </tr>
                    </xsl:for-each>
                </table>

                <h2>Registro de Clientes</h2>
                <table>
                    <tr>
                        <th>DNI</th>
                        <th>Nombre y Apellidos</th>
                        <th>Teléfono de Contacto</th>
                    </tr>
                    <xsl:for-each select="javadrive/clientes/cliente">
                        <tr>
                            <td><xsl:value-of select="dni"/></td>
                            <td><xsl:value-of select="nombre"/></td>
                            <td><xsl:value-of select="telefono"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>