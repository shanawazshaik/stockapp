from nsetools import Nse

class StockAPI:
    def __init__(self):
        self.nse = Nse()
        
    def getQuote(self, symbol):
        return self.nse.get_quote(symbol)
    def getStocksDetails(self, searchSymbol):
        stocks = self.nse.get_stock_codes()
       # print (stocks)
        return {symbol:companyName for (symbol, companyName) in stocks.items() if searchSymbol in symbol}
stockAPI = StockAPI()
print (stockAPI.getStocksDetails('INF'))