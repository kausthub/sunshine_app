import urllib2
import urllib

# Prepare the data
query_args = { 'q':'query string', 'foo':'bar' }
url='http://www.pythonforbeginners.com'
# This urlencodes your data (that's why we need to import urllib at the top)
data = urllib.urlencode(query_args)

# Send HTTP POST request
request = urllib2.Request(url, data)

response = urllib2.urlopen(request)
 
html = response.read()

# Print the result
print html