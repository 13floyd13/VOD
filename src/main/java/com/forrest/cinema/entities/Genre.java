/**
 * 
 */
package com.forrest.cinema.entities;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandles.Lookup;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * @author martin
 *
 */

@Entity
@Table(name = "genre")
public class Genre implements Serializable {
	
	private static final long serialVersionUID = 5594374522174974600L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_genre")
	private Long idGenre;
	private String nameGenre;
	private String descriptionGenre;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "genre_film",
			joinColumns = @JoinColumn(name = "id_genre"),
			inverseJoinColumns = @JoinColumn(name = "id_film")
	)
	private List<Film> films = new ArrayList<>();

	
	
	/**
	 * @return
	 * @see java.lang.String#length()
	 */
	public int length() {
		return descriptionGenre.length();
	}

	/**
	 * @return
	 * @see java.lang.String#isEmpty()
	 */
	public boolean isEmpty() {
		return descriptionGenre.isEmpty();
	}

	/**
	 * @param index
	 * @return
	 * @see java.lang.String#charAt(int)
	 */
	public char charAt(int index) {
		return descriptionGenre.charAt(index);
	}

	/**
	 * @param index
	 * @return
	 * @see java.lang.String#codePointAt(int)
	 */
	public int codePointAt(int index) {
		return descriptionGenre.codePointAt(index);
	}

	/**
	 * @param index
	 * @return
	 * @see java.lang.String#codePointBefore(int)
	 */
	public int codePointBefore(int index) {
		return descriptionGenre.codePointBefore(index);
	}

	/**
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 * @see java.lang.String#codePointCount(int, int)
	 */
	public int codePointCount(int beginIndex, int endIndex) {
		return descriptionGenre.codePointCount(beginIndex, endIndex);
	}

	/**
	 * @param index
	 * @param codePointOffset
	 * @return
	 * @see java.lang.String#offsetByCodePoints(int, int)
	 */
	public int offsetByCodePoints(int index, int codePointOffset) {
		return descriptionGenre.offsetByCodePoints(index, codePointOffset);
	}

	/**
	 * @param srcBegin
	 * @param srcEnd
	 * @param dst
	 * @param dstBegin
	 * @see java.lang.String#getChars(int, int, char[], int)
	 */
	public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
		descriptionGenre.getChars(srcBegin, srcEnd, dst, dstBegin);
	}

	/**
	 * @param srcBegin
	 * @param srcEnd
	 * @param dst
	 * @param dstBegin
	 * @deprecated
	 * @see java.lang.String#getBytes(int, int, byte[], int)
	 */
	public void getBytes(int srcBegin, int srcEnd, byte[] dst, int dstBegin) {
		descriptionGenre.getBytes(srcBegin, srcEnd, dst, dstBegin);
	}

	/**
	 * @param charsetName
	 * @return
	 * @throws UnsupportedEncodingException
	 * @see java.lang.String#getBytes(java.lang.String)
	 */
	public byte[] getBytes(String charsetName) throws UnsupportedEncodingException {
		return descriptionGenre.getBytes(charsetName);
	}

	/**
	 * @param charset
	 * @return
	 * @see java.lang.String#getBytes(java.nio.charset.Charset)
	 */
	public byte[] getBytes(Charset charset) {
		return descriptionGenre.getBytes(charset);
	}

	/**
	 * @return
	 * @see java.lang.String#getBytes()
	 */
	public byte[] getBytes() {
		return descriptionGenre.getBytes();
	}

	/**
	 * @param anObject
	 * @return
	 * @see java.lang.String#equals(java.lang.Object)
	 */
	public boolean equals(Object anObject) {
		return descriptionGenre.equals(anObject);
	}

	/**
	 * @param sb
	 * @return
	 * @see java.lang.String#contentEquals(java.lang.StringBuffer)
	 */
	public boolean contentEquals(StringBuffer sb) {
		return descriptionGenre.contentEquals(sb);
	}

	/**
	 * @param cs
	 * @return
	 * @see java.lang.String#contentEquals(java.lang.CharSequence)
	 */
	public boolean contentEquals(CharSequence cs) {
		return descriptionGenre.contentEquals(cs);
	}

	/**
	 * @param anotherString
	 * @return
	 * @see java.lang.String#equalsIgnoreCase(java.lang.String)
	 */
	public boolean equalsIgnoreCase(String anotherString) {
		return descriptionGenre.equalsIgnoreCase(anotherString);
	}

	/**
	 * @param anotherString
	 * @return
	 * @see java.lang.String#compareTo(java.lang.String)
	 */
	public int compareTo(String anotherString) {
		return descriptionGenre.compareTo(anotherString);
	}

	/**
	 * @param str
	 * @return
	 * @see java.lang.String#compareToIgnoreCase(java.lang.String)
	 */
	public int compareToIgnoreCase(String str) {
		return descriptionGenre.compareToIgnoreCase(str);
	}

	/**
	 * @param toffset
	 * @param other
	 * @param ooffset
	 * @param len
	 * @return
	 * @see java.lang.String#regionMatches(int, java.lang.String, int, int)
	 */
	public boolean regionMatches(int toffset, String other, int ooffset, int len) {
		return descriptionGenre.regionMatches(toffset, other, ooffset, len);
	}

	/**
	 * @param ignoreCase
	 * @param toffset
	 * @param other
	 * @param ooffset
	 * @param len
	 * @return
	 * @see java.lang.String#regionMatches(boolean, int, java.lang.String, int, int)
	 */
	public boolean regionMatches(boolean ignoreCase, int toffset, String other, int ooffset, int len) {
		return descriptionGenre.regionMatches(ignoreCase, toffset, other, ooffset, len);
	}

	/**
	 * @param prefix
	 * @param toffset
	 * @return
	 * @see java.lang.String#startsWith(java.lang.String, int)
	 */
	public boolean startsWith(String prefix, int toffset) {
		return descriptionGenre.startsWith(prefix, toffset);
	}

	/**
	 * @param prefix
	 * @return
	 * @see java.lang.String#startsWith(java.lang.String)
	 */
	public boolean startsWith(String prefix) {
		return descriptionGenre.startsWith(prefix);
	}

	/**
	 * @param suffix
	 * @return
	 * @see java.lang.String#endsWith(java.lang.String)
	 */
	public boolean endsWith(String suffix) {
		return descriptionGenre.endsWith(suffix);
	}

	/**
	 * @return
	 * @see java.lang.String#hashCode()
	 */
	public int hashCode() {
		return descriptionGenre.hashCode();
	}

	/**
	 * @param ch
	 * @return
	 * @see java.lang.String#indexOf(int)
	 */
	public int indexOf(int ch) {
		return descriptionGenre.indexOf(ch);
	}

	/**
	 * @param ch
	 * @param fromIndex
	 * @return
	 * @see java.lang.String#indexOf(int, int)
	 */
	public int indexOf(int ch, int fromIndex) {
		return descriptionGenre.indexOf(ch, fromIndex);
	}

	/**
	 * @param ch
	 * @return
	 * @see java.lang.String#lastIndexOf(int)
	 */
	public int lastIndexOf(int ch) {
		return descriptionGenre.lastIndexOf(ch);
	}

	/**
	 * @param ch
	 * @param fromIndex
	 * @return
	 * @see java.lang.String#lastIndexOf(int, int)
	 */
	public int lastIndexOf(int ch, int fromIndex) {
		return descriptionGenre.lastIndexOf(ch, fromIndex);
	}

	/**
	 * @param str
	 * @return
	 * @see java.lang.String#indexOf(java.lang.String)
	 */
	public int indexOf(String str) {
		return descriptionGenre.indexOf(str);
	}

	/**
	 * @param str
	 * @param fromIndex
	 * @return
	 * @see java.lang.String#indexOf(java.lang.String, int)
	 */
	public int indexOf(String str, int fromIndex) {
		return descriptionGenre.indexOf(str, fromIndex);
	}

	/**
	 * @param str
	 * @return
	 * @see java.lang.String#lastIndexOf(java.lang.String)
	 */
	public int lastIndexOf(String str) {
		return descriptionGenre.lastIndexOf(str);
	}

	/**
	 * @param str
	 * @param fromIndex
	 * @return
	 * @see java.lang.String#lastIndexOf(java.lang.String, int)
	 */
	public int lastIndexOf(String str, int fromIndex) {
		return descriptionGenre.lastIndexOf(str, fromIndex);
	}

	/**
	 * @param beginIndex
	 * @return
	 * @see java.lang.String#substring(int)
	 */
	public String substring(int beginIndex) {
		return descriptionGenre.substring(beginIndex);
	}

	/**
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 * @see java.lang.String#substring(int, int)
	 */
	public String substring(int beginIndex, int endIndex) {
		return descriptionGenre.substring(beginIndex, endIndex);
	}

	/**
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 * @see java.lang.String#subSequence(int, int)
	 */
	public CharSequence subSequence(int beginIndex, int endIndex) {
		return descriptionGenre.subSequence(beginIndex, endIndex);
	}

	/**
	 * @param str
	 * @return
	 * @see java.lang.String#concat(java.lang.String)
	 */
	public String concat(String str) {
		return descriptionGenre.concat(str);
	}

	/**
	 * @param oldChar
	 * @param newChar
	 * @return
	 * @see java.lang.String#replace(char, char)
	 */
	public String replace(char oldChar, char newChar) {
		return descriptionGenre.replace(oldChar, newChar);
	}

	/**
	 * @param regex
	 * @return
	 * @see java.lang.String#matches(java.lang.String)
	 */
	public boolean matches(String regex) {
		return descriptionGenre.matches(regex);
	}

	/**
	 * @param s
	 * @return
	 * @see java.lang.String#contains(java.lang.CharSequence)
	 */
	public boolean contains(CharSequence s) {
		return descriptionGenre.contains(s);
	}

	/**
	 * @param regex
	 * @param replacement
	 * @return
	 * @see java.lang.String#replaceFirst(java.lang.String, java.lang.String)
	 */
	public String replaceFirst(String regex, String replacement) {
		return descriptionGenre.replaceFirst(regex, replacement);
	}

	/**
	 * @param regex
	 * @param replacement
	 * @return
	 * @see java.lang.String#replaceAll(java.lang.String, java.lang.String)
	 */
	public String replaceAll(String regex, String replacement) {
		return descriptionGenre.replaceAll(regex, replacement);
	}

	/**
	 * @param target
	 * @param replacement
	 * @return
	 * @see java.lang.String#replace(java.lang.CharSequence, java.lang.CharSequence)
	 */
	public String replace(CharSequence target, CharSequence replacement) {
		return descriptionGenre.replace(target, replacement);
	}

	/**
	 * @param regex
	 * @param limit
	 * @return
	 * @see java.lang.String#split(java.lang.String, int)
	 */
	public String[] split(String regex, int limit) {
		return descriptionGenre.split(regex, limit);
	}

	/**
	 * @param regex
	 * @return
	 * @see java.lang.String#split(java.lang.String)
	 */
	public String[] split(String regex) {
		return descriptionGenre.split(regex);
	}

	/**
	 * @param locale
	 * @return
	 * @see java.lang.String#toLowerCase(java.util.Locale)
	 */
	public String toLowerCase(Locale locale) {
		return descriptionGenre.toLowerCase(locale);
	}

	/**
	 * @return
	 * @see java.lang.String#toLowerCase()
	 */
	public String toLowerCase() {
		return descriptionGenre.toLowerCase();
	}

	/**
	 * @param locale
	 * @return
	 * @see java.lang.String#toUpperCase(java.util.Locale)
	 */
	public String toUpperCase(Locale locale) {
		return descriptionGenre.toUpperCase(locale);
	}

	/**
	 * @return
	 * @see java.lang.String#toUpperCase()
	 */
	public String toUpperCase() {
		return descriptionGenre.toUpperCase();
	}

	/**
	 * @return
	 * @see java.lang.String#trim()
	 */
	public String trim() {
		return descriptionGenre.trim();
	}

	/**
	 * @return
	 * @see java.lang.String#strip()
	 */
	public String strip() {
		return descriptionGenre.strip();
	}

	/**
	 * @return
	 * @see java.lang.String#stripLeading()
	 */
	public String stripLeading() {
		return descriptionGenre.stripLeading();
	}

	/**
	 * @return
	 * @see java.lang.String#stripTrailing()
	 */
	public String stripTrailing() {
		return descriptionGenre.stripTrailing();
	}

	/**
	 * @return
	 * @see java.lang.String#isBlank()
	 */
	public boolean isBlank() {
		return descriptionGenre.isBlank();
	}

	/**
	 * @return
	 * @see java.lang.String#lines()
	 */
	public Stream<String> lines() {
		return descriptionGenre.lines();
	}

	/**
	 * @param n
	 * @return
	 * @see java.lang.String#indent(int)
	 */
	public String indent(int n) {
		return descriptionGenre.indent(n);
	}

	/**
	 * @return
	 * @see java.lang.String#stripIndent()
	 */
	public String stripIndent() {
		return descriptionGenre.stripIndent();
	}

	/**
	 * @return
	 * @see java.lang.String#translateEscapes()
	 */
	public String translateEscapes() {
		return descriptionGenre.translateEscapes();
	}

	/**
	 * @param <R>
	 * @param f
	 * @return
	 * @see java.lang.String#transform(java.util.function.Function)
	 */
	public <R> R transform(Function<? super String, ? extends R> f) {
		return descriptionGenre.transform(f);
	}

	/**
	 * @return
	 * @see java.lang.String#toString()
	 */
	public String toString() {
		return descriptionGenre.toString();
	}

	/**
	 * @return
	 * @see java.lang.String#chars()
	 */
	public IntStream chars() {
		return descriptionGenre.chars();
	}

	/**
	 * @return
	 * @see java.lang.String#codePoints()
	 */
	public IntStream codePoints() {
		return descriptionGenre.codePoints();
	}

	/**
	 * @return
	 * @see java.lang.String#toCharArray()
	 */
	public char[] toCharArray() {
		return descriptionGenre.toCharArray();
	}

	/**
	 * @param args
	 * @return
	 * @see java.lang.String#formatted(java.lang.Object[])
	 */
	public String formatted(Object... args) {
		return descriptionGenre.formatted(args);
	}

	/**
	 * @return
	 * @see java.lang.String#intern()
	 */
	public String intern() {
		return descriptionGenre.intern();
	}

	/**
	 * @param count
	 * @return
	 * @see java.lang.String#repeat(int)
	 */
	public String repeat(int count) {
		return descriptionGenre.repeat(count);
	}

	/**
	 * @return
	 * @see java.lang.String#describeConstable()
	 */
	public Optional<String> describeConstable() {
		return descriptionGenre.describeConstable();
	}

	/**
	 * @param lookup
	 * @return
	 * @see java.lang.String#resolveConstantDesc(java.lang.invoke.MethodHandles.Lookup)
	 */
	public String resolveConstantDesc(Lookup lookup) {
		return descriptionGenre.resolveConstantDesc(lookup);
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.List#contains(java.lang.Object)
	 */
	public boolean contains(Object o) {
		return films.contains(o);
	}

	/**
	 * @param e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add(Film e) {
		return films.add(e);
	}

	/**
	 * @param c
	 * @return
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	public boolean addAll(Collection<? extends Film> c) {
		return films.addAll(c);
	}

	/**
	 * @param index
	 * @param c
	 * @return
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	public boolean addAll(int index, Collection<? extends Film> c) {
		return films.addAll(index, c);
	}

	/**
	 * @return the films
	 */
	public List<Film> getFilms() {
		return films;
	}

	/**
	 * @param films the films to set
	 */
	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public Genre() {
		super();
	}

	public Genre(String nameGenre) {
		super();
		this.nameGenre = nameGenre;
	}

	/**
	 * @return the idGenre
	 */
	public Long getIdGenre() {
		return idGenre;
	}

	/**
	 * @param idGenre the idGenre to set
	 */
	public void setIdGenre(Long idGenre) {
		this.idGenre = idGenre;
	}

	/**
	 * @return the nameGenre
	 */
	public String getNameGenre() {
		return nameGenre;
	}

	/**
	 * @param nameGenre the nameGenre to set
	 */
	public void setNameGenre(String nameGenre) {
		this.nameGenre = nameGenre;
	}

	/**
	 * @return the descriptionGenre
	 */
	public String getDescriptionGenre() {
		return descriptionGenre;
	}

	/**
	 * @param descriptionGenre the descriptionGenre to set
	 */
	public void setDescriptionGenre(String descriptionGenre) {
		this.descriptionGenre = descriptionGenre;
	}
	
	
	
	

}
