<?php

class Block
{
    public $timestamp;
    public $data;
    public $previousHash;
    public $hash;

    public function __construct($timestamp, $data, $previousHash = '')
    {
        $this->timestamp = $timestamp;
        $this->data = $data;
        $this->previousHash = $previousHash;
        $this->hash = $this->calculateHash();
    }

    public function calculateHash()
    {
        return hash('sha256', $this->timestamp . $this->data . $this->previousHash);
    }
}

class Blockchain
{
    public $chain = [Block::class];

    public function __construct()
    {
        $this->chain[] = $this->createGenesisBlock();
    }

    public function createGenesisBlock()
    {
        return new Block(time(), "Genesis block", "0");
    }

    public function getLatestBlock()
    {
        return $this->chain[count($this->chain) - 1];
    }

    public function addBlock($newBlock)
    {
        $newBlock->previousHash = $this->getLatestBlock()->hash;
        $newBlock->hash = $newBlock->calculateHash();
        $this->chain[] = $newBlock;
    }

    public function isChainValid()
    {
        for ($i = 1; $i < count($this->chain); $i++) {
            $currentBlock = $this->chain[$i];
            $previousBlock = $this->chain[$i - 1];

            if ($currentBlock->hash !== $currentBlock->calculateHash()) {
                return false;
            }

            if ($currentBlock->previousHash !== $previousBlock->hash) {
                return false;
            }
        }

        return true;
    }
}

$blockchain = new Blockchain();

$blockchain->addBlock(new Block(time(), "Some data"));

$blockchain->addBlock(new Block(time(), "Some dataa"));

echo "Is blockchain valid? " . ($blockchain->isChainValid() ? "Yes" : "No");
