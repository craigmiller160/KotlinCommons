package io.craigmiller160.kotlin.commons

import java.io.InputStream
import java.io.Reader
import java.math.BigDecimal
import java.net.URL
import java.sql.*
import java.sql.Date
import java.util.*
import kotlin.collections.HashMap

class MockPreparedStatement : PreparedStatement {

    val params: MutableMap<Int,Any?> = mutableMapOf()

    override fun setRef(parameterIndex: Int, x: Ref?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBlob(parameterIndex: Int, x: Blob?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBlob(parameterIndex: Int, inputStream: InputStream?, length: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBlob(parameterIndex: Int, inputStream: InputStream?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCharacterStream(parameterIndex: Int, reader: Reader?, length: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCharacterStream(parameterIndex: Int, reader: Reader?, length: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCharacterStream(parameterIndex: Int, reader: Reader?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setArray(parameterIndex: Int, x: java.sql.Array?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getResultSetType(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDate(parameterIndex: Int, x: Date?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDate(parameterIndex: Int, x: Date?, cal: Calendar?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearParameters() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getConnection(): Connection {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setObject(parameterIndex: Int, x: Any?, targetSqlType: Int) {
        params += parameterIndex to x
    }

    override fun setObject(parameterIndex: Int, x: Any?) {
        params += parameterIndex to x
    }

    override fun setObject(parameterIndex: Int, x: Any?, targetSqlType: Int, scaleOrLength: Int) {
        params += parameterIndex to x
    }

    override fun setMaxFieldSize(max: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBytes(parameterIndex: Int, x: ByteArray?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLong(parameterIndex: Int, x: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setClob(parameterIndex: Int, x: Clob?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setClob(parameterIndex: Int, reader: Reader?, length: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setClob(parameterIndex: Int, reader: Reader?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWarnings(): SQLWarning {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun executeQuery(): ResultSet {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun executeQuery(sql: String?): ResultSet {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUnicodeStream(parameterIndex: Int, x: InputStream?, length: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun close() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isClosed(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setNString(parameterIndex: Int, value: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMaxFieldSize(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setURL(parameterIndex: Int, x: URL?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUpdateCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRowId(parameterIndex: Int, x: RowId?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setFloat(parameterIndex: Int, x: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setFetchDirection(direction: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFetchSize(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTime(parameterIndex: Int, x: Time?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTime(parameterIndex: Int, x: Time?, cal: Calendar?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun executeBatch(): IntArray {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getQueryTimeout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isPoolable(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBinaryStream(parameterIndex: Int, x: InputStream?, length: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBinaryStream(parameterIndex: Int, x: InputStream?, length: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBinaryStream(parameterIndex: Int, x: InputStream?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setNCharacterStream(parameterIndex: Int, value: Reader?, length: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setNCharacterStream(parameterIndex: Int, value: Reader?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setInt(parameterIndex: Int, x: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getGeneratedKeys(): ResultSet {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getResultSetConcurrency(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getResultSet(): ResultSet {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDouble(parameterIndex: Int, x: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun closeOnCompletion() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getParameterMetaData(): ParameterMetaData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun executeUpdate(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun executeUpdate(sql: String?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun executeUpdate(sql: String?, autoGeneratedKeys: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun executeUpdate(sql: String?, columnIndexes: IntArray?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun executeUpdate(sql: String?, columnNames: Array<out String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearBatch() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCloseOnCompletion(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T : Any?> unwrap(iface: Class<T>?): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMaxRows(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setSQLXML(parameterIndex: Int, xmlObject: SQLXML?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBigDecimal(parameterIndex: Int, x: BigDecimal?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setString(parameterIndex: Int, x: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAsciiStream(parameterIndex: Int, x: InputStream?, length: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAsciiStream(parameterIndex: Int, x: InputStream?, length: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAsciiStream(parameterIndex: Int, x: InputStream?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setNClob(parameterIndex: Int, value: NClob?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setNClob(parameterIndex: Int, reader: Reader?, length: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setNClob(parameterIndex: Int, reader: Reader?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isWrapperFor(iface: Class<*>?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setNull(parameterIndex: Int, sqlType: Int) {
        params += parameterIndex to Types.NULL
    }

    override fun setNull(parameterIndex: Int, sqlType: Int, typeName: String?) {
        params += parameterIndex to Types.NULL
    }

    override fun setMaxRows(max: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTimestamp(parameterIndex: Int, x: Timestamp?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTimestamp(parameterIndex: Int, x: Timestamp?, cal: Calendar?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setEscapeProcessing(enable: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setCursorName(name: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute(sql: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute(sql: String?, autoGeneratedKeys: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute(sql: String?, columnIndexes: IntArray?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute(sql: String?, columnNames: Array<out String>?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPoolable(poolable: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setShort(parameterIndex: Int, x: Short) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setFetchSize(rows: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearWarnings() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMetaData(): ResultSetMetaData {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addBatch() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addBatch(sql: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setQueryTimeout(seconds: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFetchDirection(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getResultSetHoldability(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBoolean(parameterIndex: Int, x: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMoreResults(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMoreResults(current: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setByte(parameterIndex: Int, x: Byte) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}